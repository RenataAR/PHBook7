package manage;

import model.User;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ProviderData {
    @DataProvider
    public Iterator<Object[]> loginModelDto(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{User.builder().email("galina@gmail.com").password("Gg123456$").build()});
        list.add(new Object[]{User.builder().email("galina@gmail.com").password("Gg123456$").build()});
        list.add(new Object[]{User.builder().email("galina@gmail.com").password("Gg123456$").build()});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> registrationCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/data.csv"));
        String line = reader.readLine();
        while (line!=null){
            String[] split = line.split(",");
            list.add(new Object[]{User.builder().email(split[2]).password(split[3]).build()});
            line = reader.readLine();
        }
        return list.iterator();
    }
}
