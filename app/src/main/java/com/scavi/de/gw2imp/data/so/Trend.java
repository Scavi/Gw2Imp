package com.scavi.de.gw2imp.data.so;

import com.scavi.de.gw2imp.data.entity.item.TrendEntity;

public class Trend {
    private final Builder mBuilder;


    /**
     * Constructor
     *
     * @param builder the builder of the constructor
     */
    private Trend(final Builder builder) {
        mBuilder = builder;
    }


    /**
     * @return the trend item entity
     */
    public TrendEntity getTrendEntity() {
        return mBuilder.mTrendEntity;
    }


    /**
     * @return the name of the header
     */
    public String getHeaderName() {
        return mBuilder.mHeaderName;
    }


    /**
     * @return <code>true</code> the current trend object is only a header object<br>
     * <code>true</code> the current trend object is an entity
     */
    public boolean isHeader() {
        return mBuilder.mIsHeader;
    }


    public static class Builder {
        private TrendEntity mTrendEntity;
        private String mHeaderName;
        private boolean mIsHeader;


        /**
         * @param trendEntity the trend item entity
         * @return this
         */
        public Builder setTrendEntity(final TrendEntity trendEntity) {
            mTrendEntity = trendEntity;
            return this;
        }


        /**
         * @param isHeader <code>true</code> the current trend object is only a header object<br>
         *                 <code>true</code> the current trend object is an entity
         * @return this
         */
        public Builder setIsHeader(final boolean isHeader) {
            mIsHeader = isHeader;
            return this;
        }


        /**
         * @param headerName the name of the header
         * @return this
         */
        public Builder setHeaderName(final String headerName) {
            mHeaderName = headerName;
            return this;
        }

        /**
         * Validate and created the trend
         *
         * @return the trend to be created
         */
        public Trend build() {
            if (mIsHeader && mHeaderName == null) {
                throw new IllegalArgumentException("Missing header name!");
            } else if (!mIsHeader && mTrendEntity == null) {
                throw new IllegalArgumentException("Missing trend entity!");
            }
            return new Trend(this);
        }
    }
}
