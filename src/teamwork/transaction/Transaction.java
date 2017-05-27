package teamwork.transaction;

public abstract class Transaction {
  public static final String ADD_NEWS_CLASS = "addNewsClass";
  public static final String ADD_NEWS = "addNews";
  public static final String ADD_TAG = "addTag";
  public static final String ADD_RELATION = "addRelation";
  public static final String POST_TAG = "postTag";
  public static final String DELETE_NEWS = "deleteNews";

  public abstract void execute();
}
