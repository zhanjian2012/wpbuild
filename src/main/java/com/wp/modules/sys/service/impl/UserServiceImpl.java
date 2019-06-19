//package com.wp.modules.sys.service.impl;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.CollectionUtils;
//import org.springframework.util.StringUtils;
//
//import com.baomidou.mybatisplus.core.conditions.Wrapper;
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import com.wp.common.PageResult;
//import com.wp.modules.sys.entity.Log;
//import com.wp.modules.sys.entity.User;
//import com.wp.modules.sys.mapper.LogMapper;
//import com.wp.modules.sys.mapper.UserMapper;
//import com.wp.modules.sys.service.UserService;
//
//import java.io.Serializable;
//import java.util.*;
//import java.util.function.Function;
//
//@Service
//public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
//
//    @Autowired
//    private UserMapper userMapper;
//
//    @Autowired
//    private RoleService roleService;
//
//    @Autowired
//    private GroupService groupService;
//
//    @Autowired
//    private OrganizationService organizationService;
//
//    @Autowired
//    private PasswordHelper passwordHelper;
//
//    @Override
//    public PageResultSet<UserDto> findByPage(UserQuery userQuery) {
//
//        if(!StringUtils.isEmpty(userQuery.getOrderBy())) {
//            PageHelper.orderBy(userQuery.getOrderBy());
//        }
//
//        Weekend<User> example = Weekend.of(User.class);
//        WeekendCriteria<User, Object> criteria = example.weekendCriteria();
//
//        if (!StringUtils.isEmpty(userQuery.getUsername())) {
//            criteria.andLike(User::getUsername, "%" + userQuery.getUsername() + "%");
//        }
//        if(userQuery.getLocked() != null) {
//            criteria.andEqualTo(User::getLocked,userQuery.getLocked());
//        }
//
//        List<UserDto> dtoList = new ArrayList<>();
//
//        PageHelper.offsetPage(userQuery.getOffset(), userQuery.getLimit());
//        userMapper.selectByExample(example).forEach(u -> {
//            UserDto dto = new UserDto(u);
//            dto.setOrganizationName(getOrganizationName(Long.valueOf(dto.getOrganizationId())));
//            dto.setRoleNames(getRoleNames(dto.getRoleIdList()));
//            dto.setGroupNames(getGroupNames(dto.getGroupIdList()));
//            dtoList.add(dto);
//        });
//
//        long total = userMapper.selectCountByExample(example);
//        PageResultSet<UserDto> resultSet = new PageResultSet<>();
//        resultSet.setRows(dtoList);
//        resultSet.setTotal(total);
//        return resultSet;
//    }
//
//    private String getGroupNames(Collection<Long> groupIds) {
//        if (CollectionUtils.isEmpty(groupIds)) {
//            return "";
//        }
//
//        StringBuilder s = new StringBuilder();
//        for (Long groupId : groupIds) {
//            Group role = groupService.findOne(groupId);
//            if (role != null) {
//                s.append(role.getName());
//                s.append(",");
//            }
//        }
//
//        if (s.length() > 0) {
//            s.deleteCharAt(s.length() - 1);
//        }
//
//        return s.toString();
//    }
//
//
//    private String getRoleNames(Collection<Long> groupIds) {
//        if (CollectionUtils.isEmpty(groupIds)) {
//            return "";
//        }
//
//        StringBuilder s = new StringBuilder();
//        for (Long roleId : groupIds) {
//            Role role = roleService.findOne(roleId);
//            if (role != null) {
//                s.append(role.getDescription());
//                s.append(",");
//            }
//        }
//
//        if (s.length() > 0) {
//            s.deleteCharAt(s.length() - 1);
//        }
//
//        return s.toString();
//    }
//
//    private String getOrganizationName(Long organizationId) {
//        Organization organization = organizationService.findOne(organizationId);
//        if (organization == null) {
//            return "";
//        }
//        return organization.getName();
//    }
//
//    @Override
//    @Transactional
//    public void createUser(User user) {
//        User u = findByUsername(user.getUsername());
//        if (u != null) {
//            throw new CrmException(ResultCodeEnum.FAILED_USER_ALREADY_EXIST);
//        }
//        // 加密密码
//        passwordHelper.encryptPassword(user);
//        userMapper.insertSelective(user);
//    }
//
//    @Override
//    @Transactional
//    public void updateUser(User user) {
//        userMapper.updateByPrimaryKeySelective(user);
//    }
//
//    @Override
//    @Transactional
//    public void deleteUser(Long userId) {
//        userMapper.deleteByPrimaryKey(userId);
//    }
//
//    @Override
//    @Transactional
//    public void changePassword(Long userId, String newPassword) {
//        User user = userMapper.selectByPrimaryKey(userId);
//        user.setPassword(newPassword);
//        passwordHelper.encryptPassword(user);
//        userMapper.updateByPrimaryKey(user);
//    }
//
//    @Override
//    public User findOne(Long userId) {
//        return userMapper.selectByPrimaryKey(userId);
//    }
//
//    @Override
//    public List<User> findAll() {
//        return userMapper.selectAll();
//    }
//
//    @Override
//    public User findByUsername(String username) {
//        User user = new User();
//        user.setUsername(username);
//        List<User> users = userMapper.select(user);
//        if(users == null) {
//        	return null;
//        }
//        if(users.size() < 1) {
//        	return null;
//        }
//        return users.get(0);
//    }
//
//    @Override
//    public Set<String> findRoles(String username) {
//        User user = findByUsername(username);
//        if (user == null) {
//            return Collections.emptySet();
//        }
//        return roleService.findRoles(user.getRoleIdList().toArray(new Long[0]));
//    }
//
//    @Override
//    public Set<String> findPermissions(String username) {
//        User user = findByUsername(username);
//        if (user == null) {
//            return Collections.emptySet();
//        }
//        return roleService.findPermissions(user.getRoleIdList().toArray(new Long[0]));
//    }
//
//	@Override
//	public boolean save(User entity) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean saveBatch(Collection<User> entityList, int batchSize) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean saveOrUpdateBatch(Collection<User> entityList, int batchSize) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean remove(Wrapper<User> queryWrapper) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean updateById(User entity) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean update(User entity, Wrapper<User> updateWrapper) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean updateBatchById(Collection<User> entityList, int batchSize) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean saveOrUpdate(User entity) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public User getOne(Wrapper<User> queryWrapper, boolean throwEx) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Map<String, Object> getMap(Wrapper<User> queryWrapper) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public int count(Wrapper<User> queryWrapper) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public List<User> list(Wrapper<User> queryWrapper) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public IPage<User> page(IPage<User> page, Wrapper<User> queryWrapper) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<Map<String, Object>> listMaps(Wrapper<User> queryWrapper) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <V> List<V> listObjs(Wrapper<User> queryWrapper, Function<? super Object, V> mapper) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public IPage<Map<String, Object>> pageMaps(IPage<User> page, Wrapper<User> queryWrapper) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public com.wp.modules.sys.service.impl.User getById(Serializable id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public PageResult<User> findByPage(User user) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void create(User user) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void update(User user) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void delete(Long userId) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public User findOne(Long userId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public User findByUsername(String username) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//}
