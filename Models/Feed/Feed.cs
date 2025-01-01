using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Receptacle.Models.Feed {
    internal class Feed {
        private IList<FeedItem> _feedItems;
        
        public Feed() { }

        public Feed(IList<FeedItem> feedItems) {
            _feedItems = feedItems;
        }

        public IList<FeedItem> feedItems { get { return _feedItems; } }

    }
}
