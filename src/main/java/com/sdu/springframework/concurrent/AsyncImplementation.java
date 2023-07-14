package com.sdu.springframework.concurrent;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AsyncImplementation {

    private static void useCompletableFuture() {
        CompletableFuture<int[]> future = CompletableFuture.supplyAsync(() -> {
            int[] res = new int[15];
            for (int i = 0; i < 10; i ++ ) {
                try {
                    Thread.sleep(200);
                    res[i] = i;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return res;
        }).thenApply((int[] s) -> {
            s[5] = 1;
            return s;
        }).thenApply((int[] s) -> {
            s[12] = 99;
            return s;
        });
        try {
            int[] res = future.get();
            System.out.println(Arrays.toString(res));
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        useCompletableFuture();
    }
}
