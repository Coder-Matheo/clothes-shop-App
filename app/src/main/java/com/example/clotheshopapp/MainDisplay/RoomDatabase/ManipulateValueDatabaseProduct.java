package com.example.clotheshopapp.MainDisplay.RoomDatabase;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ManipulateValueDatabaseProduct {
    private static final String TAG = ManipulateValueDatabaseProduct.class.getSimpleName();
    Context context;
    //int bioId, String messagePost, Integer like
    public ManipulateValueDatabaseProduct(Context context1){
        this.context = context1;
    }

    public Boolean insert_single_tweet(int bioId, String messageTweet, Integer like) throws ExecutionException, InterruptedException {

        Toast.makeText(context, "Tweet Posted", Toast.LENGTH_SHORT).show();

        ExecutorService executorInsert = Executors.newSingleThreadExecutor();
        Callable<Boolean> callable = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {

                MySingletonRoom_Product_DB.getInstance(context)
                        .databasePost_dao()
                        .insert(new ProductDataInternal(1,messageTweet, 4));
                return true;
            }
        };
        Future<Boolean> future = executorInsert.submit(callable);
        executorInsert.shutdown();
        Boolean inserted = future.get();
        return inserted;
    }




    public List<String> getAllPostTweet()
            throws ExecutionException, InterruptedException {
        ArrayList<String> returnTweetList = new ArrayList<>();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Callable<ArrayList<String>> callable = new Callable<ArrayList<String>>() {
            @Override
            public ArrayList<String> call() {
                List<ProductDataInternal> tweetPostList = MySingletonRoom_Product_DB.getInstance(context)
                        .databasePost_dao()
                        .getAllTweetPost();

                for (int i = 0; i < tweetPostList.size(); i++){
                    returnTweetList.add(tweetPostList.get(i).getMessagePost());
                }
                return returnTweetList;
            }
        };
        Future<ArrayList<String>> future = executor.submit(callable);
        // future.get() returns 2 or raises an exception if the thread dies, so safer
        executor.shutdown();
        List s = future.get();
        return s;
    }

    public void updateLikePost(String id, String likeUnlike){
        new Thread(new Runnable() {
            @Override
            public void run() {
                ProductDataInternal postLikeId = MySingletonRoom_Product_DB.getInstance(context.getApplicationContext())
                        .databasePost_dao()
                        .findByText(id);


                Log.i(TAG, "run: "+ postLikeId.getUid()+ " : "+ likeUnlike);
                try{
                    if (postLikeId != null && likeUnlike == "like"){

                        postLikeId.setLike(postLikeId.getLike() + 1);

                        MySingletonRoom_Product_DB.getInstance(context.getApplicationContext())
                                .databasePost_dao()
                                .updateLike(postLikeId);
                        Log.i(TAG, "run: "+ postLikeId.getLike());
                    }else if (postLikeId != null && likeUnlike == "unlike"){

                        postLikeId.setLike(postLikeId.getLike() - 1);

                        MySingletonRoom_Product_DB.getInstance(context.getApplicationContext())
                                .databasePost_dao()
                                .updateLike(postLikeId);
                        Log.i(TAG, "run: "+ postLikeId.getLike());
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
