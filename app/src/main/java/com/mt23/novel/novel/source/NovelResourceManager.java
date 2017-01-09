package com.mt23.novel.novel.source;

import com.mt23.novel.utils.Promise.Promiser;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by mathcoder23 on 17/1/9.
 */
public class NovelResourceManager {
    public Promiser<String,String> searchNovelByName(String name){

        Promiser<String,String> promise = new Promiser<String,String>((Promiser.Resolver<String> resolve, Promiser.Rejecter<String> reject) ->{
            int DELAY = 500;

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    resolve.run("I'm done !"); //resolving
                    reject.run("404"); //rejecting
                }
            }, DELAY);
        });
        return promise;
    }

}
