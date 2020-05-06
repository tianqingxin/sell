package com.imooc.sell.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@ToString
@NoArgsConstructor
@DynamicUpdate //动态更新时间
public class PruductCategory {

    /**
     * 类目ID
     * 自增自动生成
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;
    /**
     * 类目名称
     */
    private String categoryName;

    public PruductCategory(String categoryName, int categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }

    /**
     * 类目编号
     */
    private int categoryType;

}
