package org.lukedowell.adventchallenge.challenges.challenge9.gui;

import org.lukedowell.adventchallenge.challenges.challenge9.City;
import org.lukedowell.adventchallenge.challenges.challenge9.CityManager;
import org.lukedowell.adventchallenge.challenges.challenge9.Tour;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ldowell on 12/10/15.
 */
public class TourGuide extends JFrame {

    private static LinkedList<Tour> tours = new LinkedList<>();

    private static DrawPanel drawPanel = new DrawPanel();

    public TourGuide() {
        initComponents();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if(!tours.isEmpty()) {

                    Tour currentTour = DrawPanel.getActiveTour();
                    if(currentTour != null) {

                        int currentSlide = tours.indexOf(currentTour);
                        int nextSlide = currentSlide + 1;
                        if(nextSlide >= tours.size() -1) {
                            nextSlide = 0;
                        }

                        DrawPanel.setActiveTour(tours.get(nextSlide));

                        System.out.println("Switched from: " + currentSlide +" to " + nextSlide);

                        revalidate();
                    }
                }
            }
        }, 1000, 1000);
    }

    private void initComponents() {

        setTitle("Tour Guide");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setMinimumSize(new Dimension(1200, 900));
        setContentPane(drawPanel);
        pack();
    }

    public static void addTour(Tour t) {
        tours.add(t);

        if(DrawPanel.getActiveTour() == null) {
            DrawPanel.setActiveTour(t);
        }
    }

    /**
     * Swing drawing board
     */
    private static class DrawPanel extends JPanel {

        private BufferedImage mapBackground;

        private static Tour activeTour;

        private static int generation = 1;

        public DrawPanel() {
            try {
                mapBackground = ImageIO.read(new File("res/map.jpeg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics graphics) {

            Graphics2D g = mapBackground.createGraphics();
            g.clearRect(0, 0, mapBackground.getWidth(), mapBackground.getHeight());

            g.setStroke(new BasicStroke(10));
            g.setColor(new Color(255, 0, 0));

            if(activeTour != null) {
                for(int i = 0 ; i < activeTour.getSize(); i++) {

                    if(i + 1 < activeTour.getSize()) {
                        City origin = activeTour.getCity(i);
                        City dest = activeTour.getCity(i + 1);

                        g.setColor(new Color(255, 0, 0));
                        g.drawLine(origin.getX(), origin.getY(), dest.getX(), dest.getY());

                        g.setColor(Color.WHITE);
                        g.drawString("Step : " + i, origin.getX(), origin.getY() + 15);
                    }
                }
            }

            for(int i = 0 ; i < CityManager.numberOfCities(); i++) {
                City city = CityManager.getCity(i);

                g.fillOval(city.getX(), city.getY(), 15, 15);

                g.setColor(Color.WHITE);
                g.drawString(city.getName(), city.getX(), city.getY() - 15);

            }





            graphics.drawImage(mapBackground, 0, 0, this);
        }

        public static Tour getActiveTour() {
            return activeTour;
        }

        public static void setActiveTour(Tour activeTour) {
            DrawPanel.activeTour = activeTour;
        }

        public static void setGeneration(int i) {
            DrawPanel.generation = i;
        }
    }
}
