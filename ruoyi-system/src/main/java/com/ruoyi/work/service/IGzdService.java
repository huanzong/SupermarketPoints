package com.ruoyi.work.service;

import com.ruoyi.work.domain.Gzd;

import java.util.List;

/**
 * 消费管理 服务层
 *
 * @date 2021-10-24
 */
public interface IGzdService {
    /**
     * 查询消费管理信息
     *
     * @param gztId 消费管理ID
     * @return 消费管理信息
     */
    Gzd selectGzdById(Integer gztId);

    /**
     * 查询消费管理列表
     *
     * @param gzd 消费管理信息
     * @return 消费管理集合
     */
    List<Gzd> selectGzdList(Gzd gzd);

    /**
     * 新增消费管理
     *
     * @param gzd 消费管理信息
     * @return 结果
     */
    int insertGzd(Gzd gzd);

    /**
     * 修改消费管理
     *
     * @param gzd 消费管理信息
     * @return 结果
     */
    int updateGzd(Gzd gzd);

    /**
     * 删除消费管理信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteGzdByIds(String ids);

}
