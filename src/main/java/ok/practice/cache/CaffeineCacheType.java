package ok.practice.cache;

import lombok.Getter;



@Getter
public enum CaffeineCacheType {

	ARTISTS("artists", 5 * 60, 10000),
	ARTIST_INFO("artistInfo", 24 * 60 * 60, 10000);

	
	
	CaffeineCacheType(String cacheName, int expiredAfterWrite, int maximumSize) {
		this.cacheName = cacheName;
		this.expiredAfterWrite = expiredAfterWrite;
		this.maximumSize = maximumSize;
	}

	private String cacheName;
	private int expiredAfterWrite;
	private int maximumSize;

}
