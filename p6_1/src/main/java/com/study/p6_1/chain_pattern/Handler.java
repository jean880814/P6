package com.study.p6_1.chain_pattern;

public abstract class Handler<T> {
    public abstract void process(User user);
    public abstract void addNext(Handler handler);

    static class Builder<T> {
        private Handler<T> head;
        private Handler<T> tail;

        public Builder<T> add(Handler handler) {
            if (head == null) {
                head = tail = handler;
                return this;
            }
            tail.addNext(handler);
            tail = handler;
            return this;
        }

        public Handler<T> build(){
            return head;
        }
    }
}
