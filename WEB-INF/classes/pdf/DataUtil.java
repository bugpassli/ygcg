public class DataUtil {
    /**
* 分割路径
* @param path
* @return 返回分割后的路径
*/
	public static String[] separatePath(String path){
		////if(StringUtils.isBlank(path)){
		//	return null;
		//}
		String[] sep = path.split("\\.");
		return new String[]{sep[0],sep[1]};
	}
}