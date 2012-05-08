package yui.classes.conf;

import static yui.classes.conf.Condition.When.after;

public class Condition {
  private String name;

  private String trigger;
  
  private String ua;

  private When when;

  public enum When {
    after, before, instead
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTrigger() {
    return trigger;
  }

  public void setTrigger(String trigger) {
    this.trigger = trigger;
  }

  public String getUa() {
    return ua;
  }

  public void setUa(String ua) {
    this.ua = ua;
  }

  public When getWhen() {
    return when;
  }

  public void setWhen(When when) {
    this.when = when;
  }
}
