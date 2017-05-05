package teamwork.model;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

public class NewsCatalog {
  private static NewsCatalog instance = null;

  private List<News> newsList;

  private NewsCatalog() {
    newsList = new ArrayList<News>();
  }

  public List<News> getNewsList() {
    return newsList;
  }

  public void setNewsList(List<News> newsList) {
    this.newsList = newsList;
  }

  public static NewsCatalog getInstance() {
    if (instance == null) {
      instance = new NewsCatalog();
    }
    return instance;
  }

  public int updateAll() {
    ThreadPool pool = new ThreadPool(15);// ���15���̵߳��̳߳�
    for (News news : newsList) {
      if (news.getContent().isEmpty()) {
        pool.add(news);// �����������µ�������ӵ��̳߳أ����̳߳ظ�������̸߳���
      }
    }
    return pool.waitFinish();// �ȴ������̸߳�����ϣ����سɹ����µ���������
  }

  public boolean add(News news) {
    if (!newsList.contains(news)) {
      return newsList.add(news);
    }
    return true;
  }

  public void remove(News news) {
    newsList.remove(news);
  }

  public void remove(int index) {
    newsList.remove(index);
  }

  public int size() {
    return newsList.size();
  }

  public boolean contains(News news) {
    return newsList.contains(news);
  }

  public News get(int i) {
    return newsList.get(i);
  }

  public int indexOf(News news) {
    return newsList.indexOf(news);
  }

  public News findPrev(News news) {
    int index = indexOf(news);
    if (index == -1) {
      return null;
    }
    index = Math.max(index - 1, 0);
    return get(index);
  }

  public News findNext(News news) {
    int index = indexOf(news);
    if (index == -1) {
      return null;
    }
    index = Math.min(index + 1, size() - 1);
    return get(index);
  }

  private class ThreadPool {
    private int max;// ������������߳���
    private Stack<News> stack;// ��ջ�ݴ���ӽ��������µ�����
    private int count;// ��¼�ɹ����µ���������
    private int threadNum;// ��¼��ǰ�߳�����

    public ThreadPool(int n) {
      max = n;
      count = 0;
      threadNum = 0;
      stack = new Stack<News>();
    }

    public void add(News news) {
      addOrRemove("add", news);
      if (shouldCreateThread()) {
        new Thread() {
          @Override
          public void run() {
            while (!stack.empty()) {
              try {
                News news = addOrRemove("remove", null);
                if (news.update()) {
                  increaseCount();
                }
              } catch (EmptyStackException e) {
                break;
              }
            }
            decreaseThread();
            isZero(false);
          }
        }.start();
      }
    }

    private synchronized boolean shouldCreateThread() {
      if (threadNum < max) {
        ++threadNum;
        return true;
      }
      return false;
    }

    private synchronized void increaseCount() {
      ++count;
    }

    private synchronized void decreaseThread() {
      --threadNum;
    }

    private synchronized boolean isZero(boolean flag) {
      if (flag && threadNum != 0) {
        try {
          wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      } else if (threadNum == 0) {
        notify();
      }
      return threadNum == 0;
    }

    private synchronized News addOrRemove(String str, News news) {
      if (str.equals("add"))
        stack.push(news);
      else if (str.equals("remove")) news = stack.pop();
      return news;
    }

    public int waitFinish() {
      isZero(true);
      return count;
    }
  }
}
