package com.bunsaned3thinking.mogu.alarm.repository.component;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.bunsaned3thinking.mogu.alarm.entity.AlarmSignal;
import com.bunsaned3thinking.mogu.alarm.repository.module.AlarmSignalRepository;
import com.bunsaned3thinking.mogu.ask.entity.Ask;
import com.bunsaned3thinking.mogu.ask.repository.module.AskRepository;
import com.bunsaned3thinking.mogu.user.entity.User;
import com.bunsaned3thinking.mogu.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AlarmSignalComponentRepositoryImpl implements AlarmSignalComponentRepository {
	private final AlarmSignalRepository alarmSignalRepository;
	private final UserRepository userRepository;
	private final AskRepository askRepository;

	@Override
	public Optional<AlarmSignal> findAlarmSignalById(Long id) {
		return alarmSignalRepository.findById(id);
	}

	@Override
	public void deleteAlarmSignalById(Long id) {
		alarmSignalRepository.deleteById(id);
	}

	@Override
	public Optional<User> findUserById(String userId) {
		return userRepository.findByUserId(userId);
	}

	@Override
	public List<AlarmSignal> findAlarmSignalByUserUid(Long uid) {
		return alarmSignalRepository.findByUserUid(uid);
	}

	@Override
	public Optional<Ask> findAskByUserUidAndPostId(Long uid, Long postId) {
		return askRepository.findByUserUidAndPostId(uid, postId);
	}

	@Override
	public AlarmSignal saveAlarmSignal(AlarmSignal alarmSignal) {
		return alarmSignalRepository.save(alarmSignal);
	}
}
