package Pack01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class WeatherDao {
	
	@Autowired
	JdbcTemplate jdbct;
	

	public List<WeatherDto> selectAll(int sint, int eint) {
		List<WeatherDto> list = new ArrayList<WeatherDto>();
		String sqlstr="select id,yyyy,mm,dd,placeno,placenm, "
				+ " naturecd,naturenm,observecd,observedetail "
				+ "from weatherlist where id between ? and ? order by id ";
		list = jdbct.query(sqlstr, new BeanPropertyRowMapper<WeatherDto>(WeatherDto.class), sint, eint);
		//System.out.println(list.size());
		return list;
	}
	public int getListCount() {
		String sqlstr=" select count(*) as cnt from weatherlist ";
		int  count = jdbct.queryForObject(sqlstr,Integer.class);
		return count;
	}
	public List selectEachpageList(int totcnt, int curpage) {
		List<WeatherDto> list = new ArrayList<WeatherDto>();
		List lists = new ArrayList();
		PagingVO pv= new PagingVO();
		//System.out.println(totcnt);
		if (totcnt==0) {
			pv.setTotalCount(getListCount());
			pv.setBeginPage(1);
			pv.setCurrentPage(1);
			pv.setEndPage(pv.getDisplayPage());
			if (pv.getTotalCount()%pv.getDisplayRow()>0) {				
				pv.setTotalPage(pv.getTotalCount()/pv.getDisplayRow()+1);
			}
			else {
				pv.setTotalPage(pv.getTotalCount()/pv.getDisplayRow());
			}							
		} else {
			pv.setTotalCount(totcnt);
			pv.setCurrentPage(curpage);
		}
		if (curpage>1) pv.setCurrentPage(curpage);
		String sqlstr="select id,yyyy,mm,dd,placeno,placenm, "
				+ " naturecd,naturenm,observecd,observedetail "
				+ "from weatherlist order by id desc limit ?,? ";		
		int limits = (curpage-1)*pv.getDisplayRow();
		list = jdbct.query(sqlstr, new BeanPropertyRowMapper<WeatherDto>(WeatherDto.class), limits, pv.getDisplayRow());
		lists.add(list);
		lists.add(pv);
		//System.out.println(list.size());
		return lists;
	}
	public List placelist() {
		List<CodeDto> ll = new ArrayList<CodeDto>();	
		String sqlstr= "select placeno as cd,placenm as cdName from weather_place order by placenm ";
		ll = jdbct.query(sqlstr, new BeanPropertyRowMapper<CodeDto>(CodeDto.class));
		return ll;
	}
	public List<CodeDto> naturelist() {
		List ll = new ArrayList<CodeDto>();	
		String sqlstr= "select naturecd as cd,naturenm as cdName from weather_nature order by naturenm ";
		ll = jdbct.query(sqlstr, new BeanPropertyRowMapper<CodeDto>(CodeDto.class));
		return ll;
	}
	public List<CodeDto> observelist() {
		List ll = new ArrayList<CodeDto>();	
		String sqlstr= "select observecd as cd,observedetail as cdName from weather_observe order by observedetail ";
		ll = jdbct.query(sqlstr, new BeanPropertyRowMapper<CodeDto>(CodeDto.class));
		return ll;
	}
	public WeatherDto selectWd(int id) {
		WeatherDto wdto = new WeatherDto();
		String sqlstr ="select id,yyyy,mm,dd,placeno,placenm, "
				+ " naturecd,naturenm,observecd,observedetail "
				+ " from weatherlist where id = ? ";
		wdto = jdbct.queryForObject(sqlstr, new BeanPropertyRowMapper<WeatherDto>(WeatherDto.class),id);
		return wdto;
	}
	public int updateWd(WeatherDto wd) {	
		String sqlstr="update weatherlist set "
				+ " yyyy= ?, mm=?, dd=?,"
				+ " placeno=?, "
				+ " placenm=( select placenm from weather_place where placeno=? ),"
				+ " naturecd=?, "
				+ " naturenm= ( select naturenm from weather_nature where naturecd=? ),"
				+ " observecd=?, "
				+ " observedetail=( select observedetail from weather_observe where observecd=? ) "
				+ " where id= ? ";
		int rsint = jdbct.update(sqlstr,wd.getYyyy(),wd.getMm(),wd.getDd(),wd.getPlaceno(),wd.getPlaceno(),wd.getNaturecd(),wd.getNaturecd(),wd.getObservecd(),wd.getObservecd(),wd.getId());
		return rsint;
	}
	public int deleteWd(int num) {
		String sqlstr="delete from weatherlist where id= ? ";
		int rsint = jdbct.update(sqlstr,num);
		return rsint;
	}
}
