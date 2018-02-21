package com.nime.ezdisco.business;

import java.util.List;

import com.nime.ezdisco.domain.ScoreItem;

/**
 * Adds mix information to a score
 * 
 * @author tamer
 *
 */
public interface MixIntegrator {
	
	List<ScoreItem> addMixes(List<ScoreItem> score);

}
