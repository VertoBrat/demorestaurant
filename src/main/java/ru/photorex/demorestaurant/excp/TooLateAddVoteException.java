package ru.photorex.demorestaurant.excp;

public class TooLateAddVoteException extends RuntimeException {
    public TooLateAddVoteException() {
        super("Too late for adding your vote, try tomorrow");
    }
}
