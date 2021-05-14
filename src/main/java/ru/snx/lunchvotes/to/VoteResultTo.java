package ru.snx.lunchvotes.to;

public class VoteResultTo {

    private final String restaurant;

    private final Integer countVotes;


    public VoteResultTo(String restaurant, Integer countVotes) {
        this.restaurant = restaurant;
        this.countVotes = countVotes;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public Integer getCountVotes() {
        return countVotes;
    }
}
