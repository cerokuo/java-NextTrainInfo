package com.subway.got.rest;

import com.google.inject.Inject;
import com.subway.got.SubwayService;
import com.subway.got.TrainQueryParams;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.security.InvalidParameterException;
import java.time.LocalTime;

/**
 * Created by cerokuo on 11/03/2017.
 */
public class SubwayInformationController {
    @Inject
    private SubwayService service;

    @GET
    @Path("trains/next/<stationName>/<line>/<direction>/<time>")
    public String nextTrain(@PathParam("stationName") final String stationName,
                            @PathParam("line") final String line,
                            @PathParam("direction") final String direction,
                            @PathParam("time") final LocalTime time) {


        TrainQueryParams paramsWithOutTime = new TrainQueryParams.TrainQueryParamsBuilder()
                .direction(direction)
                .lineName(line)
                .stationName(stationName)
                .time(time)
                .build();
        String timeNextTrain;

        try{
            timeNextTrain = service.getNextTrainTime(paramsWithOutTime);
        }catch(InvalidParameterException e) {
            return e.toString();
        }
        return timeNextTrain;
    }
}
