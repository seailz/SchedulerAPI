package com.seailz.scheduler;

import lombok.experimental.UtilityClass;

import java.util.function.Supplier;

/**
 * A small API for scheduling tasks.
 *
 * @author Seailz
 */
@UtilityClass
public class SchedulerAPI {

    /**
     * Schedules a repeating task to be executed every x milliseconds.
     * @param runnable The task to be executed.
     * @param interval The interval in milliseconds.
     * <p></p>
     * Here's an example of how you'd implement this method:
     * <pre>
     * {@code
     *     SchedulerAPI.scheduleRepeat(() -> {
     *          System.out.println("Hello world, it's been a second!");
     *      ), 1000);
     * }
     * }
     * </pre>
     * @since 1.0
     */
    public static void scheduleRepeat(Runnable runnable, int interval) {
        scheduleRepeatIfTrue(runnable, interval, () -> true);
    }

    /**
     * Schedules a task to be executed after x milliseconds if a condition is true.
     * Does the same thing as {@link #scheduleRepeat(Runnable, int)} but with a condition check.
     * @param runnable The task to be executed.
     * @param interval The interval in milliseconds.
     * @param condition The condition to be met before executing the task. This is a {@link Supplier} so it can be dynamic and gets a new instance of the variable.
     * <p></p>
     * Here's an example of how you'd implement this method:
     * <pre>
     *   {@code
     *   boolean condition = true;
     *   int i = 0;
     *   scheduleRepeatIfTrue(() -> {
     *    System.out.println(i);
     *    i++;
     *   }, 1000, () -> condition);
     *   }
     * </pre>
     *
     * @since 1.0
     */
    public static void scheduleRepeatIfTrue(Runnable runnable, int interval, Supplier<Boolean> condition) {
        new Thread(() -> {
            while (condition.get()) {
                try {
                    Thread.sleep(interval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runnable.run();
            }
        }).start();
    }

    /**
     * Schedules a task to be executed after x milliseconds.
     * @param runnable The task to be executed.
     * @param interval The amount of time to wait in milliseconds.
     *
     * <p>Here's an example of how to use it:
     * <pre>
     *    {@code
     *     SchedulerAPI.scheduleAfter(() -> {
     *         System.out.println("Hello world, it's been a second!");
     *         }, 1000);
     *     }
     *  </pre>
     *
     * @since 1.0
     */
    public static void scheduleAfter(Runnable runnable, int interval) {
        new Thread(() -> {
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            runnable.run();
        }).start();
    }

    /**
     * Schedules a task to be executed after x milliseconds if a condition is true.
     * Does the same thing as {@link #scheduleAfter(Runnable, int)} but with a condition check.
     *
     * @param runnable The task to be executed.
     * @param interval The amount of time to wait in milliseconds.
     * @param condition The condition to be met before executing the task. This is a {@link Supplier} so it can be dynamic and gets a new instance of the variable.
     *
     * Here's an example of how you'd implement this method:
     *  <pre>
     *      {@code
     *          boolean condition = true;
     *          int i = 0;
     *          scheduleAfterIfTrue(() -> {
     *             System.out.println(i);
     *          }, 1000, () -> condition);
     *       }
     *   </pre>
     *
     * @since 1.0
     */
    public static void scheduleAfterIfTrue(Runnable runnable, int interval, Supplier<Boolean> condition) {
        new Thread(() -> {
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (condition.get()) {
                runnable.run();
            }
        }).start();
    }

}