package com.quinn.util.base.model;

import com.quinn.util.base.api.ClassDivAble;
import com.quinn.util.constant.enums.OrderByTypeEnum;

import java.util.Comparator;

/**
 * 类大小比较器
 *
 * @author Qunhua.Liao
 * @since 2020-04-09
 */
public class ClassComparator implements Comparator<ClassDivAble> {

    private OrderByTypeEnum order;

    public ClassComparator() {
        this.order = OrderByTypeEnum.ASC;
    }

    public ClassComparator(OrderByTypeEnum order) {
        if (order == null) {
            this.order = OrderByTypeEnum.ASC;
        } else {
            this.order = order;
        }
    }

    @Override
    public int compare(ClassDivAble o1, ClassDivAble o2) {
        if (o1 == null && o2 == null) {
            return 0;
        }

        if (o1 == null) {
            return 1 * order.status;
        }

        if (o2 == null) {
            return -1 * order.status;
        }

        if (o1.getDivClass().isAssignableFrom(o2.getDivClass())) {
            return 1 * order.status;
        } else if (o2.getDivClass().isAssignableFrom(o1.getDivClass())) {
            return -1 * order.status;
        } else {
            return 0;
        }
    }

}
