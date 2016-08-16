package com.health.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Data {
	public static final String[] majorSymps = { "急症", "常见症状", "全身状态",
			"皮肤、黏膜、淋巴结", "眼", "耳鼻喉", "口", "呼吸", "循环", "消化", "泌尿", "内分泌、代谢" };
	public static final String[][] detailSymps = {
			{ "发热", "呼吸困难", "咯血", "心悸", "胸痛", "恶心、呕吐", "腹泻", "晕厥", "昏迷" },
			{ "头痛", "头晕", "心悸", "胸闷", "胸痛", "咳嗽", "咳痰", "呼吸困难", "多饮", "多尿",
					"体重下降", "乏力", "关节肿痛", "视力模糊", "手脚麻木", "尿急", "尿痛", "便秘",
					"腹泻", "恶心呕吐", "眼花", "耳鸣", "乳房胀痛" },
			{ "寒战", "畏寒", "头晕", "水肿", "发热" },
			{ "皮疹", "水疱", "瘙痒", "紫癜", "皮肤黄染", "淋巴结肿大" },
			{ "结膜充血", "复视", "眼球突出", "眼痛", "夜盲", "溢泪", "视物模糊", "畏光", "眼睑下垂",
					"上睑挛缩" },
			{ "嘶哑", "鼻后涕", "鼻塞", "耳漏", "咽痛", "耳鸣", "听力丧失" },
			{ "牙龈出血", "牙龈肿胀", "口干", "多涎", "口腔异味" },
			{ "咳嗽", "咳痰", "呼吸困难", "呼吸急促", "咯血" },
			{ "血压升高", "胸痛", "胸闷", "心悸", "发绀", "下肢水肿" },
			{ "腹胀", "腹痛", "腹部肿块", "腹泻", "便秘", "吞咽困难", "嗳气", "黑便", "恶心呕吐", "呕血",
					"便血", "呃逆", "反酸" },
			{ "无尿", "少尿", "多尿", "血尿", "尿频", "尿急", "尿痛", "排尿困难" },
			{ "无汗", "多汗", "男性乳房发育", "月经过多", "月经过少", "烦渴", "多食", "多饮" } };
	public static final Map<String, LinkedHashMap<String, String[]>> PHYSCIAL_MAP = new HashMap<String, LinkedHashMap<String, String[]>>() {
		{
			put("全身状况", new LinkedHashMap<String, String[]>() {
				{
					put("体温", new String[] { "低温（低于正常值）","正常","低烧","高烧"});
					put("脉搏", new String[] { "低","正常","高" });
					put("血压", new String[] { "低","正常","较高","极高" });
					put("发育", new String[] { "良好", "中等", "差" });
					put("体型", new String[] { "无力型", "正力型", "超力型" });
					put("营养状态", new String[] { "营养不良", "营养中等", "营养良好", "营养过度" });
					put("意识状态", new String[] { "意识清楚", "嗜睡", "昏睡", "浅昏迷",
							"深昏迷", "谵妄" });
					put("语调与语态", new String[] { "声音嘶哑", "音调变浊", "发音困难", "音调降低",
							"语言共鸣消失", "节奏紊乱" });
					put("面容", new String[] { "无异常", "急性病容", "慢性病容", "贫血面容",
							"肝病面容", "肾病面容", "甲亢面容", "黏液性水肿面容", "二尖瓣面容",
							"肢端肥大症面容", "伤寒面容", "苦笑面容", "满月面容", "面具面容" });
					put("体位", new String[] { "自动体位", "被动体位", "强迫仰卧位", "强迫侧卧位",
							"强迫坐位", "强迫蹲位", "强迫停立位", "辗转体位", "角弓反张位" });
					put("姿势", new String[] { "正常", "异常" });
					put("步态", new String[] { "蹒跚步态", "醉酒步态", "共济失调步态", "慌张步态",
							"跨阈步态", "剪刀步态", "间歇性跛行" });
				}
			});

			put("皮肤", new LinkedHashMap<String, String[]>() {
				{
					put("颜色", new String[] { "正常", "苍白", "发红", "发绀", "黄染",
							"色素沉着", "色素脱失" });
					put("皮肤湿度与出汗", new String[] { "正常", "出汗过多", "无汗" });
					put("皮肤弹性", new String[] { "正常", "减退" });
					put("皮疹", new String[] { "无", "斑疹", "丘疹", "斑丘疹", "荨麻疹",
							"玫瑰疹", "疱疹" });
					put("出血点和紫癜", new String[] { "无", "瘀点", "紫癜", "瘀斑", "血肿" });
					put("蜘蛛痣", new String[] { "有", "无" });
					put("毛发", new String[] { "分布正常", "体毛脱落", "体毛异常增多" });
					put("水肿", new String[] { "无", "轻度", "中度", "重度" });
					put("其他",
							new String[] { "妊娠纹", "紫纹", "瘢痕", "皮下气肿", "皮下结节" });

				}
			});

			put("淋巴结", new LinkedHashMap<String, String[]>() {
				{
					put("耳前淋巴结", new String[] { "无异常", "可触及" });
					put("耳后淋巴结", new String[] { "无异常", "可触及" });
					put("枕淋巴结", new String[] { "无异常", "可触及" });
					put("颌下淋巴结", new String[] { "无异常", "可触及" });
					put("颏下淋巴结", new String[] { "无异常", "可触及" });
					put("颈前淋巴结", new String[] { "无异常", "可触及" });
					put("颈后淋巴结", new String[] { "无异常", "可触及" });
					put("腋窝淋巴结", new String[] { "无异常", "可触及" });
					put("滑车上淋巴结", new String[] { "无异常", "可触及" });
					put("腹股沟淋巴结", new String[] { "无异常", "可触及" });
					put("腘窝淋巴结", new String[] { "无异常", "可触及" });
				}
			});

			put("头发、头皮、头颅", new LinkedHashMap<String, String[]>() {
				{
					put("头发", new String[] { "无异常", "异常" });
					put("头皮", new String[] { "无异常", "异常" });
					put("头颅", new String[] { "小颅", "尖颅", "方颅", "巨颅", "长颅",
							"变形颅" });
				}
			});

			put("眼", new LinkedHashMap<String, String[]>() {
				{
					put("左侧视力", new String[] { "低倍近视","高倍近视","正常","低倍远视","高倍远视"});
					put("右侧视力", new String[] { "低倍近视","高倍近视","正常","低倍远视","高倍远视" });
					put("视野", new String[] { "正常", "视野缺失" });
					put("色觉", new String[] { "正常", "色弱", "色盲" });
					put("眼睑", new String[] { "正常", "睑内翻", "上睑下垂", "眼睑闭合障碍",
							"眼睑水肿" });
					put("泪囊", new String[] { "正常", "分泌物" });
					put("结膜", new String[] { "正常", "充血", "颗粒", "滤泡", "黄染",
							"出血点", "大片出血" });
					put("结膜", new String[] { "正常", "充血", "颗粒", "滤泡", "黄染",
							"出血点", "大片出血" });
					put("眼球外形", new String[] { "正常", "突出", "下陷" });
					put("眼球运动", new String[] { "正常", "障碍", "眼球震颤" });
					put("角膜", new String[] { "正常", "混浊", "云翳", "白斑", "软化",
							"溃疡", "新生血管", "老年环", "Kayser-Fleischerhuang环" });
					put("巩膜", new String[] { "正常", "黄染" });
					put("虹膜",
							new String[] { "正常", "纹理模糊", "纹理消失", "形态异常", "裂孔" });

				}
			});

			put("耳", new LinkedHashMap<String, String[]>() {
				{
					put("耳廓", new String[] { "正常", "畸形", "外伤瘢痕", "红肿", "瘘口",
							"低垂耳", "结节", "触痛", "牵拉痛" });
					put("外耳道", new String[] { "正常", "红肿", "痒痛", "溢液", "堵塞" });
					put("中耳", new String[] { "正常", "鼓膜穿孔", "溢脓" });
					put("乳突", new String[] { "无压痛", "压痛", "瘘管", "皮肤红肿" });
					put("粗测听力", new String[] { "正常", "减退" });
				}
			});
			put("鼻", new LinkedHashMap<String, String[]>() {
				{
					put("鼻外形", new String[] { "正常", "色素沉着", "红色斑块", "酒渣鼻",
							"蛙状鼻", "鞍鼻" });
					put("鼻翼扇动", new String[] { "有", "无" });
					put("鼻出血", new String[] { "无", "左侧", "右侧", "双侧" });
					put("鼻腔黏膜", new String[] { "正常", "肿胀", "萎缩" });
					put("鼻腔分泌物", new String[] { "无异常分泌物", "清稀无色分泌物", "脓性分泌物" });
					put("鼻窦", new String[] { "无压痛", "上颌窦压痛", "额窦压痛", "筛窦压痛" });
				}
			});
			put("口", new LinkedHashMap<String, String[]>() {
				{
					put("口唇", new String[] { "正常", "苍白", "深红", "发绀", "干燥",
							"皲裂", "疱疹", "唇裂", "肿胀", "肥厚", "红色斑片、加压褪色", "口角糜烂" });
					put("口腔黏膜", new String[] { "无异常", "色素沉着", "出血点", "瘀斑",
							"黏膜斑", "黏膜疹", "黏膜溃疡" });
					put("牙", new String[] { "无异常", "龋齿", "残根", "缺牙", "义齿",
							"黄褐色", "Hutchinson齿" });
					put("牙龈", new String[] { "正常，压迫无出血、溢脓", "水肿　□牙龈缘出血",
							"挤压后溢脓　□牙龈缘色素沉着" });
					put("舌", new String[] { "无异常", "干燥舌", "舌体增大", "地图舌", "裂纹舌",
							"草莓舌", "牛肉舌", "镜面舌", "毛舌", "震颤", "偏斜" });
					put("咽部黏膜", new String[] { "正常", "充血", "红肿", "淋巴滤泡增生" });
					put("扁桃体", new String[] { "无肿大", "Ⅰ度肿大", "Ⅱ度肿大", "Ⅲ度肿大",
							"分泌物" });
					put("口腔气味", new String[] { "臭味", "腥臭味", "血腥味", "烂苹果味",
							"尿味", "肝臭味", "大蒜味" });
					put("咽部黏膜", new String[] { "无肿大", "肿大" });
				}
			});
			put("颈部", new LinkedHashMap<String, String[]>() {
				{
					put("颈部姿势与运动", new String[] { "斜颈", "颈部运动受限", "颈部强直" });
					put("颈部皮肤与包块", new String[] { "无", "蜘蛛痣", "疖", "痈", "瘢痕",
							"瘘管", "包块" });
					put("颈部血管", new String[] { "颈静脉正常", "颈静脉充盈", "颈动脉明显搏动",
							"颈动脉血管杂音", "颈静脉血管杂音" });
					put("甲状腺", new String[] { "无肿大", "Ⅰ度肿大", "Ⅱ度肿大", "Ⅲ度肿大",
							"甲状腺结节", "甲状腺肿块", "峡部增厚", "血管杂音" });
					put("气管", new String[] { "居中", "向左移位", "向右移位", "Oliver征" });

				}
			});
			put("胸壁、胸廓", new LinkedHashMap<String, String[]>() {
				{
					put("胸壁静脉", new String[] { "无异常", "充盈", "曲张", "血流自下而上",
							"血流自上而下" });
					put("皮下气肿", new String[] { "无", "可触及" });
					put("胸壁压痛", new String[] { "无", "有" });
					put("肋间隙", new String[] { "正常", "回缩", "膨隆" });
					put("胸廓", new String[] { "正常", "扁平胸", "桶状胸", "漏斗胸", "鸡胸",
							"胸廓膨隆", "局部隆起" });

				}
			});

			put("乳房", new LinkedHashMap<String, String[]>() {
				{
					put("对称性", new String[] { "基本对称", "左侧增大", "右侧增大", "左侧缩小",
							"右侧缩小" });
					put("皮肤改变", new String[] { "正常", "发红", "溃疡", "色素沉着", "瘢痕",
							"水肿", "回缩" });
					put("乳头", new String[] { "正常", "内陷", "分泌物", "出血", "色素沉着" });
					put("腋窝和锁骨上窝", new String[] { "无异常", "包块", "红肿", "溃疡",
							"瘘管", "瘢痕" });
					put("硬度和弹性", new String[] { "硬度增加", "弹性消失" });
					put("乳房压痛", new String[] { "无", "有" });
					put("乳房包块", new String[] { "未触及", "可触及" });
				}
			});
			put("肺和胸膜", new LinkedHashMap<String, String[]>() {
				{
					put("呼吸运动", new String[] { "正常", "吸气性呼吸困难", "呼气性呼吸困难",
							"混合性呼吸困难" });
					put("呼吸深度", new String[] { "呼吸浅快", "呼吸深快" });
					put("呼吸节律", new String[] { "正常", "潮式呼吸", "间停呼吸", "抑制性呼吸",
							"叹气样呼吸" });
					put("胸廓扩张度", new String[] { "正常", "左侧受限", "右侧受限", "双侧受限" });
					put("语音震颤", new String[] { "正常", "增强", "减弱", "消失" });
					put("胸叩诊音", new String[] { "清音", "过清音", "鼓音", "浊音", "实音" });
					put("肺上界", new String[] { "正常", "变宽", "变窄" });
					put("肺前界", new String[] { "浊音区扩大", "浊音区缩小" });
					put("异常呼吸音", new String[] { "未闻及", "可闻及" });
					put("啰音", new String[] { "未闻及", "可闻及" });
					put("语音共振", new String[] { "正常", "增强", "减弱" });
					put("胸膜摩擦音", new String[] { "未闻及", "可闻及" });

				}
			});

			put("心脏", new LinkedHashMap<String, String[]>() {
				{
					put("心率", new String[] { "" });
					put("心前区隆起", new String[] { "无", "有" });
					put("心尖搏动强度", new String[] { "正常", "增强", "减弱" });
					put("负性心尖搏动", new String[] { "未见", "可见" });
					put("剑突下搏动", new String[] { "有", "无" });
					put("心底部搏动", new String[] { "有", "无" });
					put("心尖震颤", new String[] { "未触及", "可触及" });
					put("心包摩擦感", new String[] { "未触及", "可触及" });
					put("心浊音界", new String[] { "正常", "扩大", "缩小" });
					put("心律", new String[] { "齐", "可闻及早搏", "绝对不齐" });
					put("心音", new String[] { "无异常", "异常" });
					put("额外心音", new String[] { "未触及", "可触及" });
					put("心脏杂音", new String[] { "未触及", "可触及" });
					put("心包摩擦音", new String[] { "未触及", "可触及" });
				}
			});

			put("血管检查", new LinkedHashMap<String, String[]>() {
				{
					put("脉率", new String[] { "" });
					put("脉律",
							new String[] { "规则", "脉搏短绌", "二联脉", "三联脉", "脱落脉" });
					put("强弱", new String[] { "增强", "减弱" });
					put("脉波", new String[] { "正常", "水冲脉", "交替脉", "奇脉", "无脉" });
					put("血管杂音及周围血管征", new String[] { "未闻及血管杂音", "可闻及血管杂音",
							"周围血管征", "枪击音", "Duroziez双重杂音", "毛细血管搏动征" });

				}
			});
			put("视诊", new LinkedHashMap<String, String[]>() {
				{
					put("腹部外形", new String[] { "全腹膨隆", "局部膨隆", "全腹凹陷", "局部凹陷" });
					put("呼吸运动", new String[] { "腹式呼吸减弱", "腹式呼吸消失", "腹式呼吸增强" });
					put("腹壁静脉", new String[] { "无曲张", "曲张" });
					put("胃肠型", new String[] { "未见", "可见" });
					put("蠕动波", new String[] { "未见", "可见" });
					put("腹壁皮疹", new String[] { "未见", "可见" });
					put("腹壁色素", new String[] { "未见", "可见" });
					put("腹壁腹纹", new String[] { "未见", "可见" });
					put("腹壁瘢痕", new String[] { "未见", "可见" });
					put("疝", new String[] { "未见", "可见" });
					put("脐部", new String[] { "未见", "可见" });
					put("腹部体毛", new String[] { "未见", "可见" });
					put("上腹部搏动", new String[] { "未见", "可见" });
				}
			});
			put("触诊", new LinkedHashMap<String, String[]>() {
				{
					put("腹壁紧张度", new String[] { "正常", "增加", "减低" });
					put("压痛", new String[] { "无", "有" });
					put("反跳痛", new String[] { "无", "有" });
					put("肝脏肋下", new String[] { "未触及", "可触及" });
					put("肝脏剑突下", new String[] { "未触及", "可触及" });
					put("肝脏质地", new String[] { "质软", "质韧", "质硬" });
					put("肝脏边缘", new String[] { "整齐", "圆钝", "锐利", "不规则" });
					put("肝脏表面状态", new String[] { "光滑", "细小结节", "不均匀结节",
							"大块状隆起", "分叶状" });
					put("肝脏叩击痛", new String[] { "无", "有" });
					put("肝颈静脉回流征", new String[] { "阴性", "阳性" });
					put("肝脏搏动", new String[] { "无", "单向性搏动", "扩张性搏动" });
					put("肝区摩擦感", new String[] { "未触及", "可触及" });
					put("肝震颤", new String[] { "无", "有" });
					put("脾脏大小", new String[] { "未触及", "可触及" });
					put("脾脏质地", new String[] { "质软", "质韧", "质硬" });
					put("脾脏边缘", new String[] { "整齐", "圆钝", "锐利", "不规则" });
					put("脾脏表面状态", new String[] { "光滑", "细小结节", "不均匀结节",
							"大块状隆起", "分叶状" });
					put("脾脏压痛", new String[] { "无", "有" });
					put("脾区摩擦感", new String[] { "未触及", "可触及" });
					put("胆囊大小", new String[] { "未触及", "可触及" });
					put("胆囊质地", new String[] { "囊性感", "实性感" });
					put("胆囊压痛", new String[] { "无", "有" });
					put("胆囊墨菲征", new String[] { "阴性", "阳性" });
					put("肾脏", new String[] { "未触及", "可触及" });
					put("膀胱", new String[] { "未触及", "可触及" });
					put("胰腺", new String[] { "未触及", "可触及" });
					put("肿块", new String[] { "未触及", "可触及" });
					put("液波震颤", new String[] { "未触及", "可触及" });
					put("振水音", new String[] { "未触及", "可触及" });
				}
			});

			put("叩诊", new LinkedHashMap<String, String[]>() {
				{
					put("腹部叩诊音", new String[] { "鼓音范围正常", "鼓音范围缩小", "鼓音范围扩大" });
					put("肝浊音界", new String[] { "扩大", "缩小", "消失" });
					put("肝脏叩击痛", new String[] { "无", "有" });
					put("胆囊叩击痛", new String[] { "无", "有" });
					put("胃泡鼓音区", new String[] { "存在", "缩小", "消失" });
					put("脾脏浊音区", new String[] { "正常", "缩小", "扩大" });
					put("移动性浊音", new String[] { "阴性", "阳性" });
					put("肋脊角叩击痛", new String[] { "无", "有" });
					put("膀胱叩诊", new String[] { "正常", "胀大" });
				}
			});

			put("听诊", new LinkedHashMap<String, String[]>() {
				{
					put("肠鸣音", new String[] { "正常", "增强", "活跃", "亢进", "减弱",
							"消失" });
					put("血管杂音", new String[] { "可闻及", "未闻及" });
					put("摩擦音", new String[] { "可闻及", "未闻及" });

				}
			});
		}
	};

}
