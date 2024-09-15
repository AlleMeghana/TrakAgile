package com.trackAgile.service;

import com.trackAgile.dto.PatrollingTrackDto;

public interface PatrollerService {

	void startPatrolling(PatrollingTrackDto patrollingTrackDto, Long loggedInEmployeeId);

}
