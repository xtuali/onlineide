package org.webteam.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class ObjectUtil {
	
	public static Object parse(JSONObject json, Class clazz)
			throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, SecurityException,
			InvocationTargetException, NoSuchMethodException, ParseException {
			
		Field[] fields = clazz.getDeclaredFields();
		Object obj = clazz.newInstance();
		Map<String, Class> fieldMap = new HashMap<String, Class>();
		for (Field field : fields) {
			fieldMap.put(field.getName(), field.getType());
		}
		Set<String> entrySet = json.keySet();

		for (String key : entrySet) {
			if (fieldMap.get(key) != null) {
				String methodName = "set" + key.substring(0, 1).toUpperCase()
						+ key.substring(1, key.length());
				Class param = fieldMap.get(key);
				Object args = json.get(key);
				if (key.equals("id")) {
					args = parse(json.getJSONObject(key), fieldMap.get(key));
				} else if (key.equals("modiUser") || key.equals("version")
						|| key.equals("modiDate")) {
					continue;
				}
				//如果参数为空则不予赋值
				if (null == args || "".equals(args.toString())) {
					continue;
				}
			//	System.out.println(fieldMap.get(key).toString());
				if (param.toString().equals("class java.lang.Integer")) {
					args = Integer.valueOf(args.toString());
				} else if (param.toString().equals("class java.lang.Long")) {
					args = Long.valueOf(args.toString());
				} else if (param.toString().equals("class java.lang.Double")) {
					args = Double.valueOf(args.toString());
				} else if (param.toString().equals("class java.lang.Short")) {
					args = Short.valueOf(args.toString());
				} else if (param.toString().equals("class java.long.Float")) {
					args = Float.valueOf(args.toString());
				} else if (param.toString().equals("class java.long.Byte")) {
					args = Byte.valueOf(args.toString());
				} else if (param.toString().equals("class java.util.Date")) {
					//	args  = Date.parse(args.toString());
				//	System.out.println(args.toString());
					try {
						Long times = (Long) JSONObject.fromObject(args).get(
								"time");
						args = new Date(times);
					} catch (JSONException e) {
						args = new SimpleDateFormat("yyyy-MM-dd").parse(args
								.toString());
					}
				}
			//	System.out.println(fieldMap.get(key));
				args = fieldMap.get(key).cast(args);
				clazz.getMethod(methodName, param).invoke(obj, args);
			}
		}
		return clazz.cast(obj);
	}
}
