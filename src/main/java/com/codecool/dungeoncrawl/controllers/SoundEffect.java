package com.codecool.dungeoncrawl.controllers;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SoundEffect {

    static File dead = new File("C:\\Users\\bojar\\Documents\\NAUKA\\CODECOOL\\JAVA\\dungeon-crawl-1-java-igadbk\\src\\main\\resources\\sound\\sound_dead.wav");
    static File drink = new File("C:\\Users\\bojar\\Documents\\NAUKA\\CODECOOL\\JAVA\\dungeon-crawl-1-java-igadbk\\src\\main\\resources\\sound\\sound_drinking.wav");
    static File hit = new File("C:\\Users\\bojar\\Documents\\NAUKA\\CODECOOL\\JAVA\\dungeon-crawl-1-java-igadbk\\src\\main\\resources\\sound\\sound_man-scream.wav");
    static File win = new File("C:\\Users\\bojar\\Documents\\NAUKA\\CODECOOL\\JAVA\\dungeon-crawl-1-java-igadbk\\src\\main\\resources\\sound\\sound_success.wav");

    public static File getDead() {return dead;}
    public static File getDrink() {return drink;}
    public static File getHit() {return hit;}
    public static File getWin() {return win;}

    public void soundPlay(File fileSound){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(fileSound);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void soundStop(File fileSound){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(fileSound);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.stop();
        } catch (Exception ex) {
        ex.printStackTrace();}

    }
}
