package com.mt23.novel.utils.Promise;

public class Promiser<S, E> {
    public enum PromiseState {
        PENDING,
        REJECTED,
        FULFILLED;
    }
    public interface Rejecter<E> {
        public void run(E e);
    }
    public interface Resolver<S> {
        public void run(S s);
    }
    public interface PromiseInitializer<T, U> {
        public void run(Resolver<T> resolve, Rejecter<U> reject);
    }
    public PromiseState state;
    private Resolver<S> _success = null;
    private Rejecter<E> _error = null;
    public PromiseInitializer<S,E > _init;

    S resolveResult;
    E rejectError;

    public Promiser(PromiseInitializer<S, E> init) {
        this._init = init;
        state = PromiseState.PENDING;
        this.go();
    }

    public Resolver<S> resolve = (S res) -> {
        state = PromiseState.FULFILLED;
        resolveResult = res;
        next();
    };

    public Rejecter<E> reject = (E err) -> {
        state = PromiseState.REJECTED;
        rejectError = err;
        next();
    };

    private void next () {
        if(state == PromiseState.FULFILLED && this._success != null) {
            this._success.run(resolveResult);

        } else if(state == PromiseState.REJECTED && this._error != null) {
            this._error.run(rejectError);

        } else {

        }
    }


    public Promiser<S, E> success(Resolver<S> pSuccess) {
        this._success = pSuccess;
        if(state == PromiseState.FULFILLED) {
            this._success.run(resolveResult);
        }
        return this;
    }

//	public Promiser<V, Exception> then(thenFunc<V> pThen) {
//		PromiseInitializer<T, Exception> c = (Resolver<V> resolve, Rejecter<Exception> reject) -> {
//			try {
//				resolve.run(pThen.run(resolveResult));
//			} catch  (Exception e) {
//				reject.run(e);
//			}
//		};
//		Promiser<v, Exception> p = new Promiser<T, Exception>(c);
//		return p;
//	}


    public Promiser<S, E> error(Rejecter<E> pError) {
        this._error = pError;
        if(state == PromiseState.REJECTED) {
            this._error.run(rejectError);
        }
        return this;
    }

    public Promiser<S, E> go() {
        _init.run(this.resolve, this.reject);
        return this;
    }
}