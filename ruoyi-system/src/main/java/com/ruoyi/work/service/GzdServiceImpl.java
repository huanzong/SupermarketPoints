package com.ruoyi.work.service;

import com.ruoyi.common.support.Convert;
import com.ruoyi.work.domain.Gzd;
import com.ruoyi.work.mapper.GzdMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 工作台应用管理 服务层实现
 *
 * @date 2021-10-24
 */
@Service
public class GzdServiceImpl implements IGzdService {
    @Autowired
    private GzdMapper gzdMapper;

    /**
     * 查询工作台应用管理信息
     *
     * @param gztId 工作台应用管理ID
     * @return 工作台应用管理信息
     */
    @Override
    public Gzd selectGzdById(Integer gztId) {
        return gzdMapper.selectGzdById(gztId);
    }

    /**
     * 查询工作台应用管理列表
     *
     * @param gzd 工作台应用管理信息
     * @return 工作台应用管理集合
     */
    @Override
    public List<Gzd> selectGzdList(Gzd gzd) {
        return gzdMapper.selectGzdList(gzd);
    }

    /**
     * 新增工作台应用管理
     *
     * @param gzd 工作台应用管理信息
     * @return 结果
     */
    @Override
    public int insertGzd(Gzd gzd) {
        return gzdMapper.insertGzd(gzd);
    }

    /**
     * 修改工作台应用管理
     *
     * @param gzd 工作台应用管理信息
     * @return 结果
     */
    @Override
    public int updateGzd(Gzd gzd) {
        return gzdMapper.updateGzd(gzd);
    }

    /**
     * 删除工作台应用管理对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteGzdByIds(String ids) {
        return gzdMapper.deleteGzdByIds(Convert.toStrArray(ids));
    }

}
