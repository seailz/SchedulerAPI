# SchedulerAPI
Simple API for scheduling tasks - here's how to use it.

## Reapting Tasks
```java
SchedulerAPI.scheduleRepeat(() -> 
    System.out.println("Hello world, it's been a second!");
), 1000);
```

This method allows you to schedule a repeating task with an interval.

## Repeating Task if Condition
```java
boolean condition = true;
int i = 0;

scheduleRepeatIfTrue(() -> {
   System.out.println(i);
   i++;
}, 1000, () -> condition);
```

This method allows you to schedule a repeating task with an interval if a condition is true.

## Schedule After
```java
SchedulerAPI.scheduleAfter(() -> {
   System.out.println("Hello world, it's been a second!");
}, 1000);
```

This method will execute a task after a specific amount of time.

## Schedule After if Condition
```java
boolean condition = true;
SchedulerAPI.scheduleAfter(() -> {
   System.out.println("Hello world, it's been a second!");
}, 1000, () -> condition);
```

This method will execute a task after a specific amount of time if a condition is true.


