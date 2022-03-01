package com.nowcoder.community.util;

import com.nowcoder.community.entity.User;
import org.springframework.stereotype.Component;

/**
 * 起到容器的作用，专门存User
 * 持有用户信息,用于代替session对象.
 */
@Component
public class HostHolder {

    // http://concurrent.redspider.group/article/01/5.html#553-threadlocal%E7%B1%BB
    // ThreadLocal采用线程隔离的方式存放数据，可以避免多线程之间出现数据访问冲突
    // set方法，能够以当前线程为key存放数据。
    // get方法，能够以当前线程为key获取数据。
    // remove，能够以当前线程为key删除数据。
    private ThreadLocal<User> users = new ThreadLocal<>();

    public void setUser(User user) {
        users.set(user);
    }

    public User getUser() {
        return users.get();
    }

    public void clear() {
        users.remove();
    }

}
