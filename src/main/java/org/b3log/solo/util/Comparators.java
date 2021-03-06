/*
 * Solo - A small and beautiful blogging system written in Java.
 * Copyright (c) 2010-2018, b3log.org & hacpai.com
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package org.b3log.solo.util;

import org.b3log.latke.logging.Level;
import org.b3log.latke.logging.Logger;
import org.b3log.solo.model.Article;
import org.b3log.solo.model.Tag;
import org.json.JSONObject;

import java.util.Comparator;

/**
 * Comparators utilities.
 *
 * @author <a href="http://88250.b3log.org">Liang Ding</a>
 * @version 2.0.0.0, Sep 20, 2018
 */
public final class Comparators {

    /**
     * Article create date comparator.
     */
    public static final ArticleCreateDateComparator ARTICLE_CREATE_DATE_COMPARATOR = new ArticleCreateDateComparator();

    /**
     * Article update date comparator.
     */
    public static final ArticleUpdateDateComparator ARTICLE_UPDATE_DATE_COMPARATOR = new ArticleUpdateDateComparator();

    /**
     * Tag reference count comparator.
     */
    public static final TagRefCntComparator TAG_REF_CNT_COMPARATOR = new TagRefCntComparator();

    /**
     * Article comparator by create date.
     *
     * @author <a href="http://88250.b3log.org">Liang Ding</a>
     * @version 1.0.1.0, Sep 17, 2018
     */
    private static final class ArticleCreateDateComparator implements Comparator<JSONObject> {

        /**
         * Logger.
         */
        private static final Logger LOGGER = Logger.getLogger(ArticleCreateDateComparator.class);

        @Override
        public int compare(final JSONObject article1, final JSONObject article2) {
            try {
                final long date1 = article1.getLong(Article.ARTICLE_CREATED);
                final long date2 = article2.getLong(Article.ARTICLE_CREATED);

                return (int) (date2 - date1);
            } catch (final Exception e) {
                LOGGER.log(Level.ERROR, "Compares create date failed", e);

                return 0;
            }
        }
    }

    /**
     * Article comparator by update date.
     *
     * @author <a href="http://88250.b3log.org">Liang Ding</a>
     * @version 1.0.1.0, Sep 17, 2018
     */
    private static final class ArticleUpdateDateComparator implements Comparator<JSONObject> {

        /**
         * Logger.
         */
        private static final Logger LOGGER = Logger.getLogger(ArticleUpdateDateComparator.class);

        @Override
        public int compare(final JSONObject article1, final JSONObject article2) {
            try {
                final long date1 = article1.getLong(Article.ARTICLE_UPDATED);
                final long date2 = article2.getLong(Article.ARTICLE_UPDATED);

                return (int) (date2 - date1);
            } catch (final Exception e) {
                LOGGER.log(Level.ERROR, "Compares update date failed", e);

                return 0;
            }
        }
    }

    /**
     * Tag comparator by reference count descent.
     *
     * @author <a href="http://88250.b3log.org">Liang Ding</a>
     * @version 2.0.0.0, Sep 20, 2018
     */
    private static final class TagRefCntComparator implements Comparator<JSONObject> {

        /**
         * Logger.
         */
        private static final Logger LOGGER = Logger.getLogger(TagRefCntComparator.class);

        @Override
        public int compare(final JSONObject tag1, final JSONObject tag2) {
            try {
                final Integer refCnt1 = tag1.getInt(Tag.TAG_REFERENCE_COUNT);
                final Integer refCnt2 = tag2.getInt(Tag.TAG_REFERENCE_COUNT);

                return refCnt2.compareTo(refCnt1);
            } catch (final Exception e) {
                LOGGER.log(Level.ERROR, "Compares tag reference count failed", e);

                return 0;
            }
        }
    }

    /**
     * Private constructor.
     */
    private Comparators() {
    }
}
