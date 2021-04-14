package com.example.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel
public class Period {
    @ApiModelProperty(value = "start date")
    private Date startDate;
    @ApiModelProperty(value = "end date")
    private Date endDate;
}
