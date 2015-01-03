import java.util.ArrayList;
import java.util.List;

import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.config.Config;
import com.qiniu.api.rsf.ListItem;
import com.qiniu.api.rsf.ListPrefixRet;
import com.qiniu.api.rsf.RSFClient;
import com.qiniu.api.rsf.RSFEofException;


public class ListPrefix {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Config.ACCESS_KEY = "dUnogGHrvDYnowxANsk063EVmxe-pTJU2TbfzX9-";
        Config.SECRET_KEY = "aG-voyQAH_atFek1fq8c-Qe6teEqcMsVN9ubGKUC";
        Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
        
        RSFClient client = new RSFClient(mac);
        String marker = "";
            
        List<ListItem> all = new ArrayList<ListItem>();
        ListPrefixRet ret = null;
        while (true) {
            ret = client.listPrifix("zhiqin-story", "story", marker, 10);
            marker = ret.marker;
            all.addAll(ret.results);
            if (!ret.ok()) {
                // no more items or error occurs
                break;
            }
        }
        if (ret.exception.getClass() != RSFEofException.class) {
            // error handler
        } 
    }
}
