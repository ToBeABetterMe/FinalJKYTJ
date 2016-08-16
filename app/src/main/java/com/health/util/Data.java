package com.health.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Data {
	public static final String[] majorSymps = { "��֢", "����֢״", "ȫ��״̬",
			"Ƥ�����Ĥ���ܰͽ�", "��", "���Ǻ�", "��", "����", "ѭ��", "����", "����", "�ڷ��ڡ���л" };
	public static final String[][] detailSymps = {
			{ "����", "��������", "��Ѫ", "�ļ�", "��ʹ", "���ġ�Ż��", "��к", "����", "����" },
			{ "ͷʹ", "ͷ��", "�ļ�", "����", "��ʹ", "����", "��̵", "��������", "����", "����",
					"�����½�", "����", "�ؽ���ʹ", "����ģ��", "�ֽ���ľ", "��", "��ʹ", "����",
					"��к", "����Ż��", "�ۻ�", "����", "�鷿��ʹ" },
			{ "��ս", "η��", "ͷ��", "ˮ��", "����" },
			{ "Ƥ��", "ˮ��", "����", "���", "Ƥ����Ⱦ", "�ܰͽ��״�" },
			{ "��Ĥ��Ѫ", "����", "����ͻ��", "��ʹ", "ҹä", "����", "����ģ��", "η��", "�����´�",
					"��������" },
			{ "˻��", "�Ǻ���", "����", "��©", "��ʹ", "����", "����ɥʧ" },
			{ "������Ѫ", "��������", "�ڸ�", "����", "��ǻ��ζ" },
			{ "����", "��̵", "��������", "��������", "��Ѫ" },
			{ "Ѫѹ����", "��ʹ", "����", "�ļ�", "���", "��֫ˮ��" },
			{ "����", "��ʹ", "�����׿�", "��к", "����", "��������", "����", "�ڱ�", "����Ż��", "ŻѪ",
					"��Ѫ", "����", "����" },
			{ "����", "����", "����", "Ѫ��", "��Ƶ", "��", "��ʹ", "��������" },
			{ "�޺�", "�ູ", "�����鷿����", "�¾�����", "�¾�����", "����", "��ʳ", "����" } };
	public static final Map<String, LinkedHashMap<String, String[]>> PHYSCIAL_MAP = new HashMap<String, LinkedHashMap<String, String[]>>() {
		{
			put("ȫ��״��", new LinkedHashMap<String, String[]>() {
				{
					put("����", new String[] { "���£���������ֵ��","����","����","����"});
					put("����", new String[] { "��","����","��" });
					put("Ѫѹ", new String[] { "��","����","�ϸ�","����" });
					put("����", new String[] { "����", "�е�", "��" });
					put("����", new String[] { "������", "������", "������" });
					put("Ӫ��״̬", new String[] { "Ӫ������", "Ӫ���е�", "Ӫ������", "Ӫ������" });
					put("��ʶ״̬", new String[] { "��ʶ���", "��˯", "��˯", "ǳ����",
							"�����", "����" });
					put("�������̬", new String[] { "����˻��", "��������", "��������", "��������",
							"���Թ�����ʧ", "��������" });
					put("����", new String[] { "���쳣", "���Բ���", "���Բ���", "ƶѪ����",
							"�β�����", "��������", "�׿�����", "�Һ��ˮ������", "���������",
							"֫�˷ʴ�֢����", "�˺�����", "��Ц����", "��������", "�������" });
					put("��λ", new String[] { "�Զ���λ", "������λ", "ǿ������λ", "ǿ�Ȳ���λ",
							"ǿ����λ", "ǿ�ȶ�λ", "ǿ��ͣ��λ", "շת��λ", "�ǹ�����λ" });
					put("����", new String[] { "����", "�쳣" });
					put("��̬", new String[] { "���ǲ�̬", "���Ʋ�̬", "����ʧ����̬", "���Ų�̬",
							"���в�̬", "������̬", "��Ъ������" });
				}
			});

			put("Ƥ��", new LinkedHashMap<String, String[]>() {
				{
					put("��ɫ", new String[] { "����", "�԰�", "����", "���", "��Ⱦ",
							"ɫ�س���", "ɫ����ʧ" });
					put("Ƥ��ʪ�������", new String[] { "����", "��������", "�޺�" });
					put("Ƥ������", new String[] { "����", "����" });
					put("Ƥ��", new String[] { "��", "����", "����", "������", "ݡ����",
							"õ����", "����" });
					put("��Ѫ������", new String[] { "��", "����", "���", "����", "Ѫ��" });
					put("֩����", new String[] { "��", "��" });
					put("ë��", new String[] { "�ֲ�����", "��ë����", "��ë�쳣����" });
					put("ˮ��", new String[] { "��", "���", "�ж�", "�ض�" });
					put("����",
							new String[] { "������", "����", "��", "Ƥ������", "Ƥ�½��" });

				}
			});

			put("�ܰͽ�", new LinkedHashMap<String, String[]>() {
				{
					put("��ǰ�ܰͽ�", new String[] { "���쳣", "�ɴ���" });
					put("�����ܰͽ�", new String[] { "���쳣", "�ɴ���" });
					put("���ܰͽ�", new String[] { "���쳣", "�ɴ���" });
					put("����ܰͽ�", new String[] { "���쳣", "�ɴ���" });
					put("����ܰͽ�", new String[] { "���쳣", "�ɴ���" });
					put("��ǰ�ܰͽ�", new String[] { "���쳣", "�ɴ���" });
					put("�����ܰͽ�", new String[] { "���쳣", "�ɴ���" });
					put("Ҹ���ܰͽ�", new String[] { "���쳣", "�ɴ���" });
					put("�������ܰͽ�", new String[] { "���쳣", "�ɴ���" });
					put("���ɹ��ܰͽ�", new String[] { "���쳣", "�ɴ���" });
					put("�N���ܰͽ�", new String[] { "���쳣", "�ɴ���" });
				}
			});

			put("ͷ����ͷƤ��ͷ­", new LinkedHashMap<String, String[]>() {
				{
					put("ͷ��", new String[] { "���쳣", "�쳣" });
					put("ͷƤ", new String[] { "���쳣", "�쳣" });
					put("ͷ­", new String[] { "С­", "��­", "��­", "��­", "��­",
							"����­" });
				}
			});

			put("��", new LinkedHashMap<String, String[]>() {
				{
					put("�������", new String[] { "�ͱ�����","�߱�����","����","�ͱ�Զ��","�߱�Զ��"});
					put("�Ҳ�����", new String[] { "�ͱ�����","�߱�����","����","�ͱ�Զ��","�߱�Զ��" });
					put("��Ұ", new String[] { "����", "��Ұȱʧ" });
					put("ɫ��", new String[] { "����", "ɫ��", "ɫä" });
					put("����", new String[] { "����", "���ڷ�", "�����´�", "�����պ��ϰ�",
							"����ˮ��" });
					put("����", new String[] { "����", "������" });
					put("��Ĥ", new String[] { "����", "��Ѫ", "����", "����", "��Ⱦ",
							"��Ѫ��", "��Ƭ��Ѫ" });
					put("��Ĥ", new String[] { "����", "��Ѫ", "����", "����", "��Ⱦ",
							"��Ѫ��", "��Ƭ��Ѫ" });
					put("��������", new String[] { "����", "ͻ��", "����" });
					put("�����˶�", new String[] { "����", "�ϰ�", "�������" });
					put("��Ĥ", new String[] { "����", "����", "����", "�װ�", "����",
							"����", "����Ѫ��", "���껷", "Kayser-Fleischerhuang��" });
					put("��Ĥ", new String[] { "����", "��Ⱦ" });
					put("��Ĥ",
							new String[] { "����", "����ģ��", "������ʧ", "��̬�쳣", "�ѿ�" });

				}
			});

			put("��", new LinkedHashMap<String, String[]>() {
				{
					put("����", new String[] { "����", "����", "������", "����", "����",
							"�ʹ���", "���", "��ʹ", "ǣ��ʹ" });
					put("�����", new String[] { "����", "����", "��ʹ", "��Һ", "����" });
					put("�ж�", new String[] { "����", "��Ĥ����", "��ŧ" });
					put("��ͻ", new String[] { "��ѹʹ", "ѹʹ", "����", "Ƥ������" });
					put("�ֲ�����", new String[] { "����", "����" });
				}
			});
			put("��", new LinkedHashMap<String, String[]>() {
				{
					put("������", new String[] { "����", "ɫ�س���", "��ɫ�߿�", "������",
							"��״��", "����" });
					put("�����ȶ�", new String[] { "��", "��" });
					put("�ǳ�Ѫ", new String[] { "��", "���", "�Ҳ�", "˫��" });
					put("��ǻ�Ĥ", new String[] { "����", "����", "ή��" });
					put("��ǻ������", new String[] { "���쳣������", "��ϡ��ɫ������", "ŧ�Է�����" });
					put("���", new String[] { "��ѹʹ", "����ѹʹ", "���ѹʹ", "ɸ�ѹʹ" });
				}
			});
			put("��", new LinkedHashMap<String, String[]>() {
				{
					put("�ڴ�", new String[] { "����", "�԰�", "���", "���", "����",
							"����", "����", "����", "����", "�ʺ�", "��ɫ��Ƭ����ѹ��ɫ", "�ڽ�����" });
					put("��ǻ�Ĥ", new String[] { "���쳣", "ɫ�س���", "��Ѫ��", "����",
							"�Ĥ��", "�Ĥ��", "�Ĥ����" });
					put("��", new String[] { "���쳣", "ȣ��", "�и�", "ȱ��", "���",
							"�ƺ�ɫ", "Hutchinson��" });
					put("����", new String[] { "������ѹ���޳�Ѫ����ŧ", "ˮ�ס�������Ե��Ѫ",
							"��ѹ����ŧ��������Եɫ�س���" });
					put("��", new String[] { "���쳣", "������", "��������", "��ͼ��", "������",
							"��ݮ��", "ţ����", "������", "ë��", "���", "ƫб" });
					put("�ʲ��Ĥ", new String[] { "����", "��Ѫ", "����", "�ܰ���������" });
					put("������", new String[] { "���״�", "����״�", "����״�", "����״�",
							"������" });
					put("��ǻ��ζ", new String[] { "��ζ", "�ȳ�ζ", "Ѫ��ζ", "��ƻ��ζ",
							"��ζ", "�γ�ζ", "����ζ" });
					put("�ʲ��Ĥ", new String[] { "���״�", "�״�" });
				}
			});
			put("����", new LinkedHashMap<String, String[]>() {
				{
					put("�����������˶�", new String[] { "б��", "�����˶�����", "����ǿֱ" });
					put("����Ƥ�������", new String[] { "��", "֩����", "��", "Ӹ", "��",
							"����", "����" });
					put("����Ѫ��", new String[] { "����������", "��������ӯ", "���������Բ���",
							"������Ѫ������", "������Ѫ������" });
					put("��״��", new String[] { "���״�", "����״�", "����״�", "����״�",
							"��״�ٽ��", "��״���׿�", "Ͽ������", "Ѫ������" });
					put("����", new String[] { "����", "������λ", "������λ", "Oliver��" });

				}
			});
			put("�رڡ�����", new LinkedHashMap<String, String[]>() {
				{
					put("�رھ���", new String[] { "���쳣", "��ӯ", "����", "Ѫ�����¶���",
							"Ѫ�����϶���" });
					put("Ƥ������", new String[] { "��", "�ɴ���" });
					put("�ر�ѹʹ", new String[] { "��", "��" });
					put("�߼�϶", new String[] { "����", "����", "��¡" });
					put("����", new String[] { "����", "��ƽ��", "Ͱ״��", "©����", "����",
							"������¡", "�ֲ�¡��" });

				}
			});

			put("�鷿", new LinkedHashMap<String, String[]>() {
				{
					put("�Գ���", new String[] { "�����Գ�", "�������", "�Ҳ�����", "�����С",
							"�Ҳ���С" });
					put("Ƥ���ı�", new String[] { "����", "����", "����", "ɫ�س���", "��",
							"ˮ��", "����" });
					put("��ͷ", new String[] { "����", "����", "������", "��Ѫ", "ɫ�س���" });
					put("Ҹ�Ѻ���������", new String[] { "���쳣", "����", "����", "����",
							"����", "��" });
					put("Ӳ�Ⱥ͵���", new String[] { "Ӳ������", "������ʧ" });
					put("�鷿ѹʹ", new String[] { "��", "��" });
					put("�鷿����", new String[] { "δ����", "�ɴ���" });
				}
			});
			put("�κ���Ĥ", new LinkedHashMap<String, String[]>() {
				{
					put("�����˶�", new String[] { "����", "�����Ժ�������", "�����Ժ�������",
							"����Ժ�������" });
					put("�������", new String[] { "����ǳ��", "�������" });
					put("��������", new String[] { "����", "��ʽ����", "��ͣ����", "�����Ժ���",
							"̾��������" });
					put("�������Ŷ�", new String[] { "����", "�������", "�Ҳ�����", "˫������" });
					put("�������", new String[] { "����", "��ǿ", "����", "��ʧ" });
					put("��ߵ����", new String[] { "����", "������", "����", "����", "ʵ��" });
					put("���Ͻ�", new String[] { "����", "���", "��խ" });
					put("��ǰ��", new String[] { "����������", "��������С" });
					put("�쳣������", new String[] { "δ�ż�", "���ż�" });
					put("����", new String[] { "δ�ż�", "���ż�" });
					put("��������", new String[] { "����", "��ǿ", "����" });
					put("��ĤĦ����", new String[] { "δ�ż�", "���ż�" });

				}
			});

			put("����", new LinkedHashMap<String, String[]>() {
				{
					put("����", new String[] { "" });
					put("��ǰ��¡��", new String[] { "��", "��" });
					put("�ļⲫ��ǿ��", new String[] { "����", "��ǿ", "����" });
					put("�����ļⲫ��", new String[] { "δ��", "�ɼ�" });
					put("��ͻ�²���", new String[] { "��", "��" });
					put("�ĵײ�����", new String[] { "��", "��" });
					put("�ļ����", new String[] { "δ����", "�ɴ���" });
					put("�İ�Ħ����", new String[] { "δ����", "�ɴ���" });
					put("��������", new String[] { "����", "����", "��С" });
					put("����", new String[] { "��", "���ż��粫", "���Բ���" });
					put("����", new String[] { "���쳣", "�쳣" });
					put("��������", new String[] { "δ����", "�ɴ���" });
					put("��������", new String[] { "δ����", "�ɴ���" });
					put("�İ�Ħ����", new String[] { "δ����", "�ɴ���" });
				}
			});

			put("Ѫ�ܼ��", new LinkedHashMap<String, String[]>() {
				{
					put("����", new String[] { "" });
					put("����",
							new String[] { "����", "�������", "������", "������", "������" });
					put("ǿ��", new String[] { "��ǿ", "����" });
					put("����", new String[] { "����", "ˮ����", "������", "����", "����" });
					put("Ѫ����������ΧѪ����", new String[] { "δ�ż�Ѫ������", "���ż�Ѫ������",
							"��ΧѪ����", "ǹ����", "Duroziez˫������", "ëϸѪ�ܲ�����" });

				}
			});
			put("����", new LinkedHashMap<String, String[]>() {
				{
					put("��������", new String[] { "ȫ����¡", "�ֲ���¡", "ȫ������", "�ֲ�����" });
					put("�����˶�", new String[] { "��ʽ��������", "��ʽ������ʧ", "��ʽ������ǿ" });
					put("���ھ���", new String[] { "������", "����" });
					put("θ����", new String[] { "δ��", "�ɼ�" });
					put("�䶯��", new String[] { "δ��", "�ɼ�" });
					put("����Ƥ��", new String[] { "δ��", "�ɼ�" });
					put("����ɫ��", new String[] { "δ��", "�ɼ�" });
					put("���ڸ���", new String[] { "δ��", "�ɼ�" });
					put("������", new String[] { "δ��", "�ɼ�" });
					put("��", new String[] { "δ��", "�ɼ�" });
					put("�겿", new String[] { "δ��", "�ɼ�" });
					put("������ë", new String[] { "δ��", "�ɼ�" });
					put("�ϸ�������", new String[] { "δ��", "�ɼ�" });
				}
			});
			put("����", new LinkedHashMap<String, String[]>() {
				{
					put("���ڽ��Ŷ�", new String[] { "����", "����", "����" });
					put("ѹʹ", new String[] { "��", "��" });
					put("����ʹ", new String[] { "��", "��" });
					put("��������", new String[] { "δ����", "�ɴ���" });
					put("���ལͻ��", new String[] { "δ����", "�ɴ���" });
					put("�����ʵ�", new String[] { "����", "����", "��Ӳ" });
					put("�����Ե", new String[] { "����", "Բ��", "����", "������" });
					put("�������״̬", new String[] { "�⻬", "ϸС���", "�����Ƚ��",
							"���״¡��", "��Ҷ״" });
					put("����ߵ��ʹ", new String[] { "��", "��" });
					put("�ξ�����������", new String[] { "����", "����" });
					put("���ಫ��", new String[] { "��", "�����Բ���", "�����Բ���" });
					put("����Ħ����", new String[] { "δ����", "�ɴ���" });
					put("�����", new String[] { "��", "��" });
					put("Ƣ���С", new String[] { "δ����", "�ɴ���" });
					put("Ƣ���ʵ�", new String[] { "����", "����", "��Ӳ" });
					put("Ƣ���Ե", new String[] { "����", "Բ��", "����", "������" });
					put("Ƣ�����״̬", new String[] { "�⻬", "ϸС���", "�����Ƚ��",
							"���״¡��", "��Ҷ״" });
					put("Ƣ��ѹʹ", new String[] { "��", "��" });
					put("Ƣ��Ħ����", new String[] { "δ����", "�ɴ���" });
					put("���Ҵ�С", new String[] { "δ����", "�ɴ���" });
					put("�����ʵ�", new String[] { "���Ը�", "ʵ�Ը�" });
					put("����ѹʹ", new String[] { "��", "��" });
					put("����ī����", new String[] { "����", "����" });
					put("����", new String[] { "δ����", "�ɴ���" });
					put("����", new String[] { "δ����", "�ɴ���" });
					put("����", new String[] { "δ����", "�ɴ���" });
					put("�׿�", new String[] { "δ����", "�ɴ���" });
					put("Һ�����", new String[] { "δ����", "�ɴ���" });
					put("��ˮ��", new String[] { "δ����", "�ɴ���" });
				}
			});

			put("ߵ��", new LinkedHashMap<String, String[]>() {
				{
					put("����ߵ����", new String[] { "������Χ����", "������Χ��С", "������Χ����" });
					put("��������", new String[] { "����", "��С", "��ʧ" });
					put("����ߵ��ʹ", new String[] { "��", "��" });
					put("����ߵ��ʹ", new String[] { "��", "��" });
					put("θ�ݹ�����", new String[] { "����", "��С", "��ʧ" });
					put("Ƣ��������", new String[] { "����", "��С", "����" });
					put("�ƶ�������", new String[] { "����", "����" });
					put("�߼���ߵ��ʹ", new String[] { "��", "��" });
					put("����ߵ��", new String[] { "����", "�ʹ�" });
				}
			});

			put("����", new LinkedHashMap<String, String[]>() {
				{
					put("������", new String[] { "����", "��ǿ", "��Ծ", "����", "����",
							"��ʧ" });
					put("Ѫ������", new String[] { "���ż�", "δ�ż�" });
					put("Ħ����", new String[] { "���ż�", "δ�ż�" });

				}
			});
		}
	};

}