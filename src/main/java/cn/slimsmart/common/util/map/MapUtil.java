package cn.slimsmart.common.util.map;

import java.util.HashMap;
import java.util.Map;

public class MapUtil {
	
	private MapUtil(){}
	
	private static double PI = 3.14159265358979324 * 3000.0 / 180.0;
	// 单位是千米
	private static double EARTH_RADIUS = 6378.137;

	/**
	 * GPS加密bd-09（百度）至 gcj-02
	 */
	public static Map<String, Double> decryptGPS(double bd_lat, double bd_lon) {
		double x = bd_lon - 0.0065, y = bd_lat - 0.006;
		double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * PI);
		double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * PI);
		double gg_lon = z * Math.cos(theta);
		double gg_lat = z * Math.sin(theta);
		Map<String, Double> map = new HashMap<String, Double>();
		map.put("lon", gg_lon);
		map.put("lat", gg_lat);
		return map;
	}
	
	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}
	
	/**
	 * 测算两点经纬度的距离
	 * 
	 * @param lat1
	 *            纬度1 eg:39129504
	 * @param lng1
	 *            经度1 eg:117248439
	 * @param lat2
	 *            纬度2
	 * @param lng2
	 *            经度2
	 * @return
	 */
	public static double getDistance(double lat1, double lng1, double lat2, double lng2) {
		lat1 = convert(lat1);
		lng1 = convert(lng1);
		lat2 = convert(lat2);
		lng2 = convert(lng2);
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000;
		return s;
	}
	
	private static double convert(double d) {
		if(d > 1000000) {
			d = d/1000000;
		}
		return d;
	}
}
