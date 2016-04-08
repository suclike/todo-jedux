package com.example.jedux.todo;

import android.app.Application;

import trikita.anvil.Anvil;
import trikita.jedux.Action;
import trikita.jedux.Store;

public class App extends Application {

    private static App sInstance;

    private Store<Action, State> store;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        State initialState = State.getDefault();

        this.store = new Store<>(new State.Reducer(), initialState);

        this.store.subscribe(Anvil::render);
    }

    public static State state() {
        return sInstance.store.getState();
    }

    public static State dispatch(Action<ActionType, ?> action) {
        return sInstance.store.dispatch(action);
    }
}

