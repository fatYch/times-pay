package com.linlibang.pay.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by huangzihao1 on 2018/9/17.
 */
@Data
@ApiModel("分页对象")
public class ResponsePageVo<T> implements Serializable {

    public ResponsePageVo() {
    }

    public ResponsePageVo(int pageNum, int pageSize, long total, int pages, List<T> data) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
        this.pages = pages;
        this.data = data;
    }

    @ApiModelProperty("第几页")
    private int pageNum;//第几页
    @ApiModelProperty("每页数量")
    private int pageSize;//每页数量
    @ApiModelProperty("总记录数")
    private long total;//总记录数
    @ApiModelProperty("总页数")
    private int pages;//总页数
    @ApiModelProperty("分页数据列表")
    private List<T> data;//数据


}
