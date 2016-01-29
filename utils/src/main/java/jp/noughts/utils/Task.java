/*

AsyncTask をシンプルに使えるラッパーです

        new Task<Void>(new Task.Job() {
            @Override
            public Void onStart() throws Exception {
                return null;
            }

            @Override
            public void onComplete(Object object) {
            }

            @Override
            public void onError(Exception e) {
            }
        });


        onCompleteの型を指定
        new Task<String>(new Task.Job<String>() {
            @Override
            public String onStart() throws Exception {
                return "結果";
            }

            @Override
            public void onComplete(String object) {
                Log.d( "foo", object ); // -> 結果
            }

            @Override
            public void onError(Exception e) {

            }
        });
 */


package jp.noughts.utils;

import android.os.AsyncTask;



public class Task<T> {

    private CustomAsyncTask mCustomAsyncTask;

    // Class constructor
    public Task(Job job) {
        mCustomAsyncTask = new CustomAsyncTask(job);
        mCustomAsyncTask.execute();
    }


    // CustomAsyncTask
    private class CustomAsyncTask extends AsyncTask<Void,Void,T> {

        private Job<T> mJob;
        private Exception mException;

        private CustomAsyncTask(Job job) {
            mJob = job;
        }

        @Override
        protected T doInBackground(Void... params) {
            try {
                return mJob.onStart();
            } catch (Exception e) {
                e.printStackTrace();
                mException = e;
                return null;
            }
        }

        @Override
        protected void onPostExecute(T type) {
            super.onPostExecute(type);
            // Checking for exceptions
            if (mException == null) {
                mJob.onComplete(type);
            } else {
                mJob.onError(mException);
                Logging.error(mException);
            }
        }
    }

    // The Job interface
    public interface Job<T> {
        public T onStart() throws Exception ;
        public void onComplete(T object);
        public void onError(Exception e);
    }
}