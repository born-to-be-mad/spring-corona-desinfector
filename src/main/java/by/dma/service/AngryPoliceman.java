package by.dma.service;

import javax.annotation.PostConstruct;

import by.dma.annotation.InjectByType;

/**
 * TODO
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public class AngryPoliceman implements Policeman {
    @InjectByType
    private  Recommendator recommendator;

    @PostConstruct
    public void init() {
        System.out.println(recommendator.getClass());
    }

    @Override
    public void makePeopleLeaveRoom() {
        System.out.println("Bang Bang! I am ready to shoot you down if you stay here!");
    }
}
