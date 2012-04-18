package yui.classes.conf;

public class Condition {
  public final String name;

  public final String trigger;

  public final String userAgent;

  public Condition(String name, String trigger) {
    this(name, trigger, null);
  }

  public Condition(String name, String trigger, String userAgent) {
    this.name = name;
    this.trigger = trigger;
    this.userAgent = userAgent;
  }
}
