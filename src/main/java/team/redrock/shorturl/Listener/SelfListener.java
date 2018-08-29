package team.redrock.shorturl.Listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.HashMap;
import java.util.Map;

@WebListener
public class SelfListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        System.err.println("MyApplicationListener初始化成功");
        ServletContext context = sce.getServletContext();
        // IP存储器
        Map<String, Long[]> ipMap = new HashMap<String, Long[]>();
        context.setAttribute("ipMap", ipMap);
        // 限制IP存储器：存储被限制的IP信息
        Map<String, Long> limitedIpMap = new HashMap<String, Long>();
        context.setAttribute("limitedIpMap", limitedIpMap);
        System.out.println("ipmap："+ipMap.toString()+";limitedIpMap:"+limitedIpMap.toString()+"初始化成功。。。。。");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // TODO Auto-generated method stub

    }

}
