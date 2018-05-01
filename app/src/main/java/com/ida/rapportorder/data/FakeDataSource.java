package com.ida.rapportorder.data;

import com.ida.rapportorder.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FakeDataSource  {

    private static final int SIZE_OF_COLLECTION = 12;
    private Random mRandom;

    private final String [] datesAndTimes = {
            "Skapad av Ida Lundgren, 2018-04-23",
            "Skapad av Niklas Lundgren, 2018-04-24",
            "Skapad av David Lundgren, 2018-04-25",
            "Skapad av Anders Isaksson, 2018-04-26"
    };

    private final String [] messages = {
            "Skellefteå Kommun 5538",
            "Polaris 5234",
            "Tekniska kontoret 5532",
            "Gräv & Schakt 4558"
    };

    private final int [] colours = {
            R.drawable.green_drawable,
            R.drawable.red_drawable,
            R.drawable.blue_drawable,
            R.drawable.yellow_drawable
    };

    public FakeDataSource() {
        mRandom = new Random();
    }

    //@Override
    /*public List<Orders> getListOfData() {
        ArrayList<Orders> listOfData = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < 12; i++){
            int randOne = random.nextInt(4);
            int randTwo = random.nextInt(4);
            int randThree = random.nextInt(4);

            Orders orderItem = new Orders(
                    colours[randOne],
                    datesAndTimes[randTwo],
                    messages[randThree]
            );

            listOfData.add(orderItem);
        }
        return listOfData;
    }*/
}
