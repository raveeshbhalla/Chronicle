# Chronicle
Chronicle is a library that is designed to help you create specific, targeted experiences for your Android app users. It’s goal is to make it easier to build in onboarding and feature discovery techniques that might otherwise be fairly complicated.

## Example

Designing for feature discovery can be complicated: we can force our users through guided tutorials, show tooltips and spotlights whenever they reach a new screen and more. The problem with such techniques is that they often lead to a firehose of information that users simply ignore or forget.

Instead, what we often find ourselves wishing we could do is get the user to learn more about our app over a period of time. For example, we might want to point out what that Floating Action Button can do the first time they're on a screen, but maybe only point out about some of the options tucked away in a Navigation Drawer much, much later. Or another common design flow is to show a user a feature only if they've already accomplished certain task(s) in the past.

The challenge for such experiences is that they tend to be technically complicated to build. Currently, most apps keep flags to track if a user has performed a certain action “once”., and decide the flow of your app accordingly. But this is still limiting - what if I cared if they'd done it more than once?

Chronicle is designed to instead work like a service that stores occurrences of key user events, but also allows you to query the analytics from your app. It is largely meant to provide a specification, for which custom implementations can be created as required. A local, SQLite implementation will be provided as well, but keeping them separate allows for easy drop-and-replace if you want to change where the data's stored. For example, you could build a Firebase implementation that allows for simple cross-device sync as well, allowing you to track a user's actions across devices and hence account for that in your experience if you so wish.

# Specification

The currently planned specification is shown below.

````
public interface ChronicleSpec {
  void did(String event);
  void did(String event, long timestamp);
  boolean hasDone(String event);
  int timesDone(String event);
  boolean hasDoneSince(String event, long timestamp);
  int timesDoneSince(String event, long timestamp);
  long timeSinceLastOccurence(String event);
  List<Record> allTimesDid(String event);
}```

Additionally, non-blocking methods and RxJava extensions for the specification are planned.

