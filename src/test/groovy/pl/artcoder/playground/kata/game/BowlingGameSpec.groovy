package pl.artcoder.playground.kata.game

import spock.lang.Specification

class BowlingGameSpec extends Specification {

    def "should calculate score for rolls containing missed ones"() {
        expect:
        BowlingGame.scoreFor("--------------------") == 0
    }

    def "should calculate score for rolls without strike or spare "() {
        expect:
        BowlingGame.scoreFor("11111111111111111111") == 20
    }

    def "should calculate score for rolls with strike ones"() {
        expect:
        BowlingGame.scoreFor("X11-----------------") == 14
        BowlingGame.scoreFor("1/35XXX458/X3/23") == 160
        BowlingGame.scoreFor("XXXXXXXXXXXX") == 300
    }

    def "should calculate score for rolls with spare ones"() {
        expect:
        BowlingGame.scoreFor("4/3-----------------") == 16
        BowlingGame.scoreFor("5/5/5/5/5/5/5/5/5/5/5") == 150
    }
}
