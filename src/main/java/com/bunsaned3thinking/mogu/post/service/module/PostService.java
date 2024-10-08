package com.bunsaned3thinking.mogu.post.service.module;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.bunsaned3thinking.mogu.post.controller.dto.request.PostRequest;
import com.bunsaned3thinking.mogu.post.controller.dto.request.UpdatePostRequest;
import com.bunsaned3thinking.mogu.post.controller.dto.response.PostResponse;
import com.bunsaned3thinking.mogu.post.controller.dto.response.PostWithDetailResponse;
import com.bunsaned3thinking.mogu.post.controller.dto.response.SearchHistoryResponse;
import com.bunsaned3thinking.mogu.post.entity.RecruitState;

public interface PostService {

	ResponseEntity<List<PostResponse>> searchPostsByKeyword(String keyword, String userId, Long cursor);

	ResponseEntity<PostWithDetailResponse> createPost(String memberId, PostRequest postRequest,
		List<String> postImageLinks);

	ResponseEntity<PostResponse> findPost(Long id);

	ResponseEntity<PostWithDetailResponse> findPostWithDetail(Long id);

	ResponseEntity<PostWithDetailResponse> updatePost(String userId, Long postId, UpdatePostRequest updatePostRequest,
		List<String> postImageLinks);

	ResponseEntity<List<PostResponse>> findAllReportedPost(Long cursor);

	ResponseEntity<List<SearchHistoryResponse>> findAllSearchHistoryByUserId(String userId);

	ResponseEntity<Void> deletePost(String userId, Long postId);

	ResponseEntity<Void> deleteSearchHistory(Long searchHistoryId, String userId);

	ResponseEntity<PostResponse> closePost(Long postId, String userId, RecruitState recruitState);

	ResponseEntity<List<PostResponse>> findAllPost(String userId, Long cursor);

	ResponseEntity<PostResponse> hidePost(Long postId, String userId, boolean state);

	ResponseEntity<List<PostResponse>> findLikedPostsByUserId(String userId, Long cursor);

	List<String> findImageNames(Long postId);

	boolean isChief(String userId, Long chatId);
}
