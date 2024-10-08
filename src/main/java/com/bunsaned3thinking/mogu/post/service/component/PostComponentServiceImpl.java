package com.bunsaned3thinking.mogu.post.service.component;

import java.util.List;
import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.bunsaned3thinking.mogu.chat.service.module.ChatService;
import com.bunsaned3thinking.mogu.heart.service.HeartService;
import com.bunsaned3thinking.mogu.image.service.ImageService;
import com.bunsaned3thinking.mogu.post.controller.dto.request.PostRequest;
import com.bunsaned3thinking.mogu.post.controller.dto.request.UpdatePostRequest;
import com.bunsaned3thinking.mogu.post.controller.dto.response.PostResponse;
import com.bunsaned3thinking.mogu.post.controller.dto.response.PostWithDetailResponse;
import com.bunsaned3thinking.mogu.post.controller.dto.response.SearchHistoryResponse;
import com.bunsaned3thinking.mogu.post.entity.RecruitState;
import com.bunsaned3thinking.mogu.post.service.module.PostService;
import com.bunsaned3thinking.mogu.report.dto.request.ReportRequest;
import com.bunsaned3thinking.mogu.report.dto.response.ReportResponse;
import com.bunsaned3thinking.mogu.report.service.module.ReportService;
import com.bunsaned3thinking.mogu.user.service.module.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PostComponentServiceImpl implements PostComponentService {
	private final PostService postService;
	private final ImageService imageService;
	private final ChatService chatService;
	private final HeartService heartService;
	private final ReportService reportService;
	private final UserService userService;

	@Override
	public ResponseEntity<PostWithDetailResponse> createPost(final String userId, final PostRequest postRequest,
		final List<MultipartFile> multipartFileList) {
		List<String> imageLinks = imageService.uploadAll(multipartFileList);
		ResponseEntity<PostWithDetailResponse> response = postService.createPost(userId, postRequest,
			imageLinks);
		chatService.createChat(Objects.requireNonNull(response.getBody()).getPost().getId());
		return response;
	}

	@Override
	public ResponseEntity<ReportResponse> createReport(Long postId, String userId, ReportRequest reportRequest) {
		return reportService.createReport(postId, userId, reportRequest);
	}

	@Override
	public ResponseEntity<PostResponse> findPost(final Long postId) {
		return postService.findPost(postId);
	}

	@Override
	public ResponseEntity<PostWithDetailResponse> findPostWithDetail(final Long postId) {
		return postService.findPostWithDetail(postId);
	}

	@Override
	public ResponseEntity<List<PostResponse>> findAllReportedPost(Long cursor) {
		return postService.findAllReportedPost(cursor);
	}

	@Override
	public ResponseEntity<List<SearchHistoryResponse>> findAllSearchHistory(String userId) {
		return postService.findAllSearchHistoryByUserId(userId);
	}

	@Override
	public ResponseEntity<Void> deleteSearchHistory(Long searchHistoryId, String userId) {
		return postService.deleteSearchHistory(searchHistoryId, userId);
	}

	@Override
	public ResponseEntity<PostWithDetailResponse> updatePost(final Long postId, final String userId,
		final UpdatePostRequest updatePostRequest, final List<MultipartFile> multipartFileList) {
		List<String> imageNames = postService.findImageNames(postId);
		List<String> imageLinks = imageService.uploadAll(multipartFileList);
		try {
			ResponseEntity<PostWithDetailResponse> response = postService.updatePost(userId, postId, updatePostRequest,
				imageLinks);
			imageService.deleteAll(imageNames);
			return response;
		} catch (RuntimeException e) {
			imageService.deleteAll(imageLinks);
			throw e;
		}
	}

	@Override
	public ResponseEntity<Void> deletePost(final String userId, final Long postId) {
		List<String> imageNames = postService.findImageNames(postId);
		ResponseEntity<Void> response = postService.deletePost(userId, postId);
		imageService.deleteAll(imageNames);
		return response;
	}

	@Override
	public ResponseEntity<PostResponse> closePost(Long postId, String userId, RecruitState recruitState) {
		ResponseEntity<PostResponse> response = postService.closePost(postId, userId, recruitState);
		userService.updateUserLevel(postId, recruitState);
		return response;
	}

	@Override
	public ResponseEntity<List<PostResponse>> findAllPosts(String userId, Long cursor) {
		return postService.findAllPost(userId, cursor);
	}

	@Override
	public ResponseEntity<PostResponse> hidePost(Long postId, String userId, boolean state) {
		return postService.hidePost(postId, userId, state);
	}

	@Override
	public ResponseEntity<PostResponse> likePost(Long postId, String userId) {
		return heartService.likePost(postId, userId);
	}

	@Override
	public ResponseEntity<Void> unlikePost(Long postId, String userId) {
		return heartService.unlikePost(postId, userId);
	}

	@Override
	public ResponseEntity<List<PostResponse>> findAllLikedPost(String userId, Long cursor) {
		return postService.findLikedPostsByUserId(userId, cursor);
	}

	@Override
	public ResponseEntity<List<PostResponse>> searchPostByKeyword(String keyword, String userId, Long cursor) {
		return postService.searchPostsByKeyword(keyword, userId, cursor);
	}
}
