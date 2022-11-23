package com.ssafy.happyhouse.notice.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.notice.model.Notice;
import com.ssafy.happyhouse.notice.model.NoticeParameter;
import com.ssafy.happyhouse.notice.model.dao.NoticeDao;

@Service
public class NoticeServiceImpl implements NoticeService {

	private NoticeDao noticeDao;

	@Autowired
	private NoticeServiceImpl(NoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}

	@Override
	public int writeNotice(Notice notice) throws SQLException {
		return noticeDao.writeNotice(notice);
	}

	@Override
	public List<Notice> listNotice(NoticeParameter noticeParameter) throws Exception {
		int start = noticeParameter.getPgNo() == 0 ? 0 : (noticeParameter.getPgNo() - 1) * noticeParameter.getSpp();
		noticeParameter.setStart(start);
		
		
		
		return noticeDao.listNotice(noticeParameter);
	}
	
//	// 정렬 다시 쓰기?
//	@Override
//	public List<Notice> listNotice(Map<String, String> map) throws SQLException {
//		Map<String, Object> param = new HashMap<String, Object>();
//		String key = map.get("key");
//		if ("userid".equals(key))
//			key = "b.user_id";
//		param.put("key", key == null ? "" : key);
//		param.put("word", map.get("word") == null ? "" : map.get("word"));
//		int pgNo = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
//		int start = pgNo * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
//		param.put("start", start);
//		param.put("listsize", SizeConstant.LIST_SIZE);
//
//		return noticeDao.listNotice(param);
//	}

	

	@Override
	public int totalNoticeCount(Map<String, String> map) throws SQLException {
		return noticeDao.totalNoticeCount(map);
	}

	@Override
	public Notice getNotice(int noticeNo) throws SQLException {
		return noticeDao.getNotice(noticeNo);
	}

	@Override
	public void updateHit(int noticeNo) throws SQLException {
		noticeDao.updateHit(noticeNo);
	}

	@Override
	public void modifyNotice(Notice notice) throws SQLException {
		noticeDao.modifyNotice(notice);
	}

	@Override
	public void deleteNotice(int noticeNo) throws SQLException {
		noticeDao.deleteNotice(noticeNo);
	}
	
	
	
	@Override
	public Map<String, Object> makePageNavigation(NoticeParameter noticeParameter) throws Exception {
		int naviSize = 5;
		
//		PageNavigation pageNavigation = new PageNavigation();
//		pageNavigation.setCurrentPage(noticeParameter.getPgNo());
//		pageNavigation.setNaviSize(naviSize);
		
		int totalCount = noticeDao.getTotalCount(noticeParameter);//총글갯수  269
//		pageNavigation.setTotalCount(totalCount);  
		
		int totalPageCount = (totalCount - 1) / noticeParameter.getSpp() + 1;//27
//		pageNavigation.setTotalPageCount(totalPageCount);
		
		boolean startRange = noticeParameter.getPgNo() <= naviSize;
//		pageNavigation.setStartRange(startRange);
		
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < noticeParameter.getPgNo();
//		pageNavigation.setEndRange(endRange);
		
		int startPage = (noticeParameter.getPgNo() - 1) / naviSize * naviSize + 1;
		int endPage = startPage + naviSize - 1;
		if(totalPageCount < endPage)
			endPage = totalPageCount;
//		pageNavigation.makeNavigator();
		
		Map<String, Object> pageNavigationMap = new HashMap<String, Object>();
		
		pageNavigationMap.put("naviSize", naviSize);
		pageNavigationMap.put("totalCount", totalCount);
		pageNavigationMap.put("totalPageCount", totalPageCount);
		pageNavigationMap.put("startRange", startRange);
		pageNavigationMap.put("endRange", endRange);
		pageNavigationMap.put("startPage", startPage);
		pageNavigationMap.put("endPage", endPage);
		
		return pageNavigationMap;
	}
	
//	@Override
//	public PageNavigation makePageNavigation(NoticeParameter noticeParameter) throws Exception {
//		int naviSize = 5;
//		
//		PageNavigation pageNavigation = new PageNavigation();
//		pageNavigation.setCurrentPage(noticeParameter.getPgNo());
//		pageNavigation.setNaviSize(naviSize);
//		
//		int totalCount = noticeDao.getTotalCount(noticeParameter);//총글갯수  269
//		pageNavigation.setTotalCount(totalCount);  
//		
//		int totalPageCount = (totalCount - 1) / noticeParameter.getSpp() + 1;//27
//		pageNavigation.setTotalPageCount(totalPageCount);
//		
//		boolean startRange = noticeParameter.getPgNo() <= naviSize;
//		pageNavigation.setStartRange(startRange);
//		
//		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < noticeParameter.getPgNo();
//		pageNavigation.setEndRange(endRange);
//		
//		pageNavigation.makeNavigator();
//		return pageNavigation;
//	}


//	public static class QuickSorter {
//		public static void quickSort(List<Notice> arr) {
//			sort(arr, 0, arr.size() - 1);
//		}
//
//		private static void sort(List<Notice> arr, int low, int high) {
//			if (low >= high) return;
//
//			int mid = partition(arr, low, high);
//			sort(arr, low, mid - 1);
//			sort(arr, mid, high);
//		}
//
//		private static int partition(List<Notice> arr, int low, int high) {
//			int pivot = arr.get((low + high) / 2).getHit();
//
//			while (low <= high) {
//				while (arr.get(low).getHit() > pivot) low++;
//				while (arr.get(high).getHit() < pivot) high--;
//				if (low <= high) {
//					swap(arr, low, high);
//					low++;
//					high--;
//				}
//			}
//			return low;
//		}
//
//		private static void swap(List<Notice> arr, int i, int j) {
//			Notice tmp = arr.get(i);
//			arr.set(i, arr.get(j));
//			arr.set(j, tmp);
//		}
//	}


//	@Override
//	public PageNavigation makePageNavigation(Map<String, String> map) throws SQLException {
//		PageNavigation pageNavigation = new PageNavigation();
//
//		int naviSize = SizeConstant.NAVIGATION_SIZE;
//		int sizePerPage = SizeConstant.LIST_SIZE;
//		int currentPage = Integer.parseInt(map.get("pgno"));
//
//		pageNavigation.setCurrentPage(currentPage);
//		pageNavigation.setNaviSize(naviSize);
//		Map<String, Object> param = new HashMap<String, Object>();
//		String key = map.get("key");
//		if ("userid".equals(key))
//			key = "user_id";
//		param.put("key", key == null ? "" : key);
//		param.put("word", map.get("word") == null ? "" : map.get("word"));
//		int totalCount = noticeDao.getTotalArticleCount(param);
//		pageNavigation.setTotalCount(totalCount);
//		int totalPageCount = (totalCount - 1) / sizePerPage + 1;
//		pageNavigation.setTotalPageCount(totalPageCount);
//		boolean startRange = currentPage <= naviSize;
//		pageNavigation.setStartRange(startRange);
//		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
//		pageNavigation.setEndRange(endRange);
//		pageNavigation.makeNavigator();
//
//		return pageNavigation;
//
//	}
}
