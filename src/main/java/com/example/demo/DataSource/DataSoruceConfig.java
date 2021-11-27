package com.example.demo.DataSource;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NameTokenizers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.awt.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class DataSoruceConfig {

    @Autowired
    private Environment env;


    public Connection getConnecton() throws SQLException {

          String url = env.getProperty("DBURL");
          String schema = env.getProperty("schema","test");
          String username = env.getProperty("DBUser","comp440");
          String password = env.getProperty("password","tarun");

          System.out.println("URL " + url);
          System.out.println("schema " + schema);
          System.out.println(("username" + username));
          System.out.println("password"+ password);
//         Connection con = DriverManager.getConnection("jdbc:postgresql://ec2-3-219-103-45.compute-1.amazonaws.com:5432/d5vkqvcbgf7ua2", "kkgjcdnfajzkfh", "0f9705e9e0d6fb16bd9bae7563766dd0dbcad4eef4237d397cbc5143bd978fae");
            Connection con = DriverManager.getConnection(url+schema,username,password);

            System.out.println(con.getSchema());
        return  con;

    }

    public Reader parseFile(String filepath) throws IOException {
//        String path = "C:\\Users\\Student\\Documents\\CSUN\\Circulam\\Fall2021\\DataBase\\demo\\demo\\src\\main\\resources\\static\\data.sql";
        File file = new File(filepath);
        FileReader fileReader = new FileReader(file);

        Reader reader = new BufferedReader(fileReader);

        return reader;
    }


    @Primary
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        mapper.getConfiguration().setSourceNameTokenizer(NameTokenizers.UNDERSCORE);
        return mapper;
    }

}
