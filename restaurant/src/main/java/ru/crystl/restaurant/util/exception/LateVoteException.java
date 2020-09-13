package ru.crystl.restaurant.util.exception;

public class LateVoteException extends RuntimeException {
    public LateVoteException(String message) {
        super(message);
    }
}
