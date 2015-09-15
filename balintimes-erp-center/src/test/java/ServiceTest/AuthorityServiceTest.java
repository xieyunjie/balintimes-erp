package ServiceTest;

import com.balintimes.erp.center.dao.ApplicationDao;
import com.balintimes.erp.center.dao.ResourceDao;
import com.balintimes.erp.center.dao.RoleDao;
import com.balintimes.erp.center.dao.UserDao;
import com.balintimes.erp.center.model.Resource;
import com.balintimes.erp.center.service.AuthorityService;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.SubjectThreadState;
import org.apache.shiro.util.ThreadState;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class AuthorityServiceTest {


    private ThreadState _threadState;
    protected Subject _mockSubject;

    @Before
    public void before() {
        _mockSubject = Mockito.mock(Subject.class);
        _threadState = new SubjectThreadState(_mockSubject);
        _threadState.bind();
    }

    @javax.annotation.Resource
    private ApplicationDao applicationDao;
    @javax.annotation.Resource
    private ResourceDao resourceDao;
    @javax.annotation.Resource
    private RoleDao roleDao;
    @javax.annotation.Resource
    private UserDao userDao;

    @javax.annotation.Resource
    private AuthorityService authorityService;

//	@Test
//	public void GetUserApplications() {
//		String username="llll";
//		 List<Application> list= this.applicationDao.GetUserApplications(username);
//		 for(Application item:list){
//			 System.out.println(item.getName());
//		 }
//	}

    @Test
    public void GetUserDisableMenus() {
        String username = "admin";
        String appUid = "05bd7806-3026-11e5-8396-c86000a05d5f";
        List<Resource> list = this.authorityService.GetUserDisableMenus(username, appUid);
        for (Resource item : list) {
            System.out.println(item.getName());
        }
    }

//    @Test
//    public void GetUserMenu() {
//        String username = "33";
//        String applicationuid = "05bd7806-3026-11e5-8396-c86000a05d5f";
//        //List<Resource> list= this.resourceDao.GetUserMenu(username, applicationuid);
//        List<Resource> list = GetUserMenu(username, applicationuid);
//        for (Resource item : list) {
//            System.out.println(item.getName());
//        }
//    }
//
//    @Test
//    public void GetUserPermissions() {
//        String username = "admin";
//        String applicationuid = "05bd7806-3026-11e5-8396-c86000a05d5f";
//        //List<Resource> list= this.resourceDao.GetUserMenu(username, applicationuid);
//        List<Resource> list = authorityService.GetUserPermissions(username,null);
//        for (Resource item : list) {
//            System.out.println(item.getName());
//        }
//    }
//
//
//    @Test
//    public void GetEmployee() {
//
//        Employee employee = this.authorityService.GetEmployee("qq");
//
//        System.out.println(employee);
//    }

//	@Test
//	public void GetUserPermissions() {
//		String username="jacky";
//		String applicationuid="05bd7806-3026-11e5-8396-c86000a05d5f";
//		List<Resource> list= this.resourceDao.GetUserPermissions(username, applicationuid);
//		for(Resource item:list){
//			 System.out.println(item.getName());
//		 }
//	}
//
//	@Test
//	public void GetUserMenuPermissions() {
//		String username="llll";
//		String menuuid="6c3fa3bf-34f9-11e5-8396-c86000a05d5f";
//		List<Resource> list= this.resourceDao.GetUserMenuPermissions(username, menuuid);
//		for(Resource item:list){
//			 System.out.println(item.getName());
//		 }
//	}
//
//	@Test
//	public void GetUserRoles() {
//		String username="jacky";
//		List<Role> list=this.roleDao.GetUserRoles(username);
//		for(Role item:list){
//			 System.out.println(item.getName());
//		 }
//	}
//	

//    private void SetResource(List<Resource> list, Resource item) {
//        if (!item.getParentUid().equals("00000000-0000-0000-0000-000000000000")) {
//            Resource r = this.resourceDao.GetResource(item.getParentUid());
//            if (r.isForbidden() == false) {
//                this.SetResource(list, r);
//            }
//        }
//        if (!this.ExistsResourceByList(list, item)) {
//            list.add(item);
//        }
//    }
//
//    private boolean ExistsResourceByList(List<Resource> list, Resource item) {
//        boolean b = false;
//        for (Resource it : list) {
//            if (it.getUid().equals(item.getUid())) {
//                b = true;
//            }
//        }
//        return b;
//    }
//
//    public List<Resource> GetUserMenu(String username, String applicationuid) {
//        User user = this.userDao.getUserByName(username);
//        List<Resource> list = new ArrayList<Resource>();
//        if (!user.isIsadmin()) {
//            List<Resource> rs = this.resourceDao.GetUserMenu(username,
//                    applicationuid);
//            for (Resource item : rs) {
//                this.SetResource(list, item);
//            }
//            return list;
//        } else {
//            List<Resource> rs = this.resourceDao
//                    .GetResourceListByNotForbidden(applicationuid);
//            for (Resource item : rs) {
//                if (item.getResourceType() == 1) {
//                    list.add(item);
//                }
//            }
//            return list;
//        }
//    }

}
