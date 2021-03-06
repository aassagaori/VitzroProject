package com.vitzro.vo;

import lombok.Data;

/* lombok을 사용하면, @Data 애노테이션을 활용해서, getter/setter, toString, hashCode, equals, canEqual 이 기본적으로 생성됩니다.
- @AllArgsConstructor : 모든 필드를 초기화할수 있는 파라메터(디폴트 생성자가 자동으로 만들어지는데 생성자를 추가하면 디폴트생성자는 날라감)
- @NoArgsConstructor : 디폴트 생성자를 만드는 어노테이션
 출처 : https://blog.naver.com/icet25/220986505844*/

@Data
public class CctvVO {
	private String cctv_mngm_nmbr	 ; //CCTV 관리 번호	(PK)		
	private String cctv_ctlr_id	     ; //CCTV 제어기 ID			    
	private String cctv_ctlr_ip	     ; //CCTV 제어기 아이피		  
	private String cctv_ctlr_port    ; //CCTV 제어기 포트			
	private String istl_lctn_nm	     ; //설치 위치 명			      
	private String istl_lctn_addr    ; //설치 위치 주소		
	private String cctv_id    		 ; //공단 id
	private Boolean m_stat = false; // CCTV 제어기 연결 정보

}
