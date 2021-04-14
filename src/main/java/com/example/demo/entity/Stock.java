package com.example.demo.entity;



import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity(name = "t_stock")
@Table(indexes = @Index(name = "symbols_date", columnList = "symbol, date", unique = true))
@ApiModel
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;


    @Column(name = "symbol", length = 6)
    @ApiModelProperty(value = "symbol")
    private String symbol;

    @NotNull
    @Temporal(TemporalType.DATE)
    @ApiModelProperty(value = "date", notes = "dataType should be yyyy-MM-dd")
    private Date date;

    @Min(value = 0, message = "invalid negative input")
    @NotNull
    @ApiModelProperty(value = "open")
    private BigDecimal open;

    @Min(value = 0, message = "invalid negative input")
    @NotNull
    @ApiModelProperty(value = "high")
    private BigDecimal high;

    @Min(value = 0, message = "invalid negative input")
    @NotNull
    @ApiModelProperty(value = "low")
    private BigDecimal low;

    @Min(value = 0, message = "invalid negative input")
    @NotNull
    @ApiModelProperty(value = "close")
    private BigDecimal close;

    @Min(value = 0, message = "invalid negative input")
    @NotNull
    @ApiModelProperty(value = "volume")
    private BigDecimal volume;

    @Min(value = 0, message = "invalid negative input")
    @NotNull
    @ApiModelProperty(value = "dividends")
    private BigDecimal dividends;

    @Min(value = 0, message = "invalid negative input")
    @NotNull
    @ApiModelProperty(value = "splits")
    private BigDecimal splits;

}
